package com.adrian.mobstersrest.mobsters.services;

import com.adrian.mobstersrest.mobsters.actions.AbstractAction;
import com.adrian.mobstersrest.mobsters.actions.AcceptAll;
import com.adrian.mobstersrest.mobsters.actions.CollectAll;
import com.adrian.mobstersrest.mobsters.actions.DailyBonus;
import com.adrian.mobstersrest.mobsters.actions.Deposit;
import com.adrian.mobstersrest.mobsters.actions.EnergyLink;
import com.adrian.mobstersrest.mobsters.actions.Login;
import com.adrian.mobstersrest.mobsters.actions.Logout;
import com.adrian.mobstersrest.mobsters.actions.Optimizer;
import com.adrian.mobstersrest.mobsters.actions.OptimizerPopup;
import com.adrian.mobstersrest.mobsters.actions.Restore;
import com.adrian.mobstersrest.mobsters.actions.RestorePopup;
import com.adrian.mobstersrest.mobsters.actions.SaveCurrent;
import com.adrian.mobstersrest.mobsters.actions.SaveCurrentPopup;
import com.adrian.mobstersrest.mobsters.actions.SendAll;
import com.adrian.mobstersrest.mobsters.actions.Withdraw;
import com.adrian.mobstersrest.mobsters.actions.WithdrawPopup;
import com.adrian.mobstersrest.mobsters.actions.tabaction.Bank;
import com.adrian.mobstersrest.mobsters.actions.tabaction.MyMob;
import com.adrian.mobstersrest.mobsters.actions.tabaction.MyMobster;
import com.adrian.mobstersrest.mobsters.actions.tabaction.Territory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Adrian
 */
@Slf4j
@Service
public class ActionFactoryServiceImpl implements ActionFactoryService {

  /**
   * Create a new AbstractAction object with the passed in actionName.
   *
   * @param actionName the name of the AbstractAction
   * @return a new AbstractAction instance.
   */
  @Override
  public AbstractAction getAbstractAction(String actionName) {
    AbstractAction abstractAction;
    switch (actionName) {
      case "Bank":
        abstractAction = new Bank();
        break;
      case "200 Energy Link":
        abstractAction = new EnergyLink();
        break;
      case "Daily Bonus Link":
        abstractAction = new DailyBonus();
        break;
      case "My Mob":
        abstractAction = new MyMob();
        break;
      case "Accept All":
        abstractAction = new AcceptAll();
        break;
      case "Optimizer Popup":
        abstractAction = new OptimizerPopup();
        break;
      case "Optimizer":
        abstractAction = new Optimizer();
        break;
      case "Save Current Popup":
        abstractAction = new SaveCurrentPopup();
        break;
      case "Save Current":
        abstractAction = new SaveCurrent();
        break;
      case "Send All":
        abstractAction = new SendAll();
        break;
      case "Collect All":
        abstractAction = new CollectAll();
        break;
      case "Restore":
        abstractAction = new Restore();
        break;
      case "Restore Popup":
        abstractAction = new RestorePopup();
        break;
      case "Deposit":
        abstractAction = new Deposit();
        break;
      case "Territory":
        abstractAction = new Territory();
        break;
      case "Withdraw":
        abstractAction = new Withdraw();
        break;
      case "Withdraw Popup":
        abstractAction = new WithdrawPopup();
        break;
      case "Logout":
        abstractAction = new Logout();
        break;
      case "Login":
        abstractAction = new Login();
        break;
      case "My Mobster":
        abstractAction = new MyMobster();
        break;

      default:
        abstractAction = null;
        log.debug("Unable to find node name: " + actionName);
        break;
    }

    return abstractAction;
  }
}