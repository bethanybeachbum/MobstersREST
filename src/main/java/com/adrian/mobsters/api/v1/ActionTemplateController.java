package com.adrian.mobsters.api.v1;

import com.adrian.mobsters.domain.ActionTemplate;
import com.adrian.mobsters.domain.ActionTemplateAction;
import com.adrian.mobsters.exception.ActionTemplateNotFoundException;
import com.adrian.mobsters.exception.ActionTemplateNotOwnedException;
import com.adrian.mobsters.repository.ActionTemplateRepository;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * A controller for interacting with {@link ActionTemplate}s.
 */
@RestController
@RequestMapping("/api/v1/action-templates")
@RequiredArgsConstructor
public class ActionTemplateController {
    private final ActionTemplateRepository actionTemplateRepository;

    @GetMapping
    public List<ActionTemplate> getUserTemplates(Principal principal) {
        return actionTemplateRepository.findAllByUser(principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ActionTemplate saveTemplate(@RequestBody ActionTemplate actionTemplate,
                                       Principal principal) {
        String id = actionTemplate.getId();

        if (id != null) {
            Optional<ActionTemplate> templateOpt = actionTemplateRepository.findByIdAndUser(id, principal.getName());

            if (templateOpt.isPresent()) {
                ActionTemplate template = templateOpt.get();

                if (!template.getUser().equalsIgnoreCase(principal.getName())) {
                    throw new ActionTemplateNotOwnedException();
                }
            }
        }

        validateTemplate(actionTemplate);
        return actionTemplateRepository.save(getWithFixedSequences(actionTemplate));
    }

    private void validateTemplate(ActionTemplate actionTemplate) {
        if (actionTemplate.getMobsters().isEmpty()) {
            throw new IllegalStateException("Template must have at least one mobster.");
        } else if(actionTemplate.getActions().isEmpty()) {
            throw new IllegalStateException("Template must have at least one action.");
        } else if (actionTemplate.getName().isEmpty()) {
            throw new IllegalStateException("Template name cannot be empty.");
        } else if (Strings.isNullOrEmpty(actionTemplate.getFrequency())) {
            throw new IllegalStateException("Template frequency cannot be empty.");
        }
    }

    private ActionTemplate getWithFixedSequences(ActionTemplate actionTemplate) {
        List<ActionTemplateAction> actions = actionTemplate.getActions();
        for (int i = 0; i < actions.size(); i++) {
            ActionTemplateAction actionTemplateAction = actions.get(i);
            actionTemplateAction.setSequence(i);
        }

        return actionTemplate;
    }

    @DeleteMapping("/{id}")
    public void deleteTemplate(@PathVariable String id, Principal principal) {
        Optional<ActionTemplate> actionTemplateOpt = actionTemplateRepository
                .findByIdAndUser(id, principal.getName());

        if (!actionTemplateOpt.isPresent()) {
            throw new ActionTemplateNotFoundException(id);
        }

        actionTemplateRepository.deleteById(id);
    }
}
