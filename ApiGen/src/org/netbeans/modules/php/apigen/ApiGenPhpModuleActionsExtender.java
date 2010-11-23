/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.php.apigen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.Action;
import org.netbeans.modules.php.apigen.actions.GenerateApiAction;
import org.netbeans.modules.php.spi.phpmodule.PhpModuleActionsExtender;

/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class ApiGenPhpModuleActionsExtender extends PhpModuleActionsExtender {

    private static final List<Action> ACTIONS;

    static {
        List<Action> actions = new ArrayList<Action>();
        actions.add(GenerateApiAction.getInstance());
        ACTIONS = Collections.unmodifiableList(actions);
    }

    @Override
    public String getMenuName() {
        return "ApiGen";
    }

    @Override
    public List<? extends Action> getActions() {
        return ACTIONS;
    }
}
