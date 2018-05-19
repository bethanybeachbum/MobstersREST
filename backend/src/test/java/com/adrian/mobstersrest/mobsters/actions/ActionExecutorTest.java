package com.adrian.mobstersrest.mobsters.actions;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;

import com.adrian.mobstersrest.mobsters.services.HumanBotService;
import com.adrian.mobstersrest.mobsters.services.MobsterService;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebWindow;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ActionExecutorTest {

  @Mock
  private WebClient webClient;
  @Mock
  private MobsterService mobsterService;
  @Mock
  private WebWindow webWindow;
  @Mock
  private HumanBotService humanBotService;
  @Mock
  private AbstractAction action;

  private ActionExecutor actionExecutor;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    actionExecutor = new ActionExecutor(mobsterService, webClient, humanBotService);
  }

  @Test
  public void executeAction() {
    given(action.isFinished()).willReturn(true);
    given(webClient.getWebWindows()).willReturn(new ArrayList<>(Arrays.asList(webWindow, webWindow)));
    assertTrue(actionExecutor.executeAction(action));
  }

  @Test
  public void notLoggedInActionShouldFail() {
    given(webClient.getWebWindows()).willReturn(new ArrayList<>(Collections.singletonList(webWindow)));

    assertFalse(actionExecutor.executeAction(action));
  }
}