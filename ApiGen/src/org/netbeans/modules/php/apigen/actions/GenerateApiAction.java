/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.netbeans.modules.php.apigen.actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;


/**
 *
 * @author Ondřej Brejla <ondrej@brejla.cz>
 */
public class GenerateApiAction extends AbstractAction {

	private static GenerateApiAction INSTANCE = new GenerateApiAction();

	protected GenerateApiAction() {
        putValue("noIconInMenu", true); // NOI18N
        putValue(NAME, "ApiGen: Generate API...");
        putValue("menuText", "Generate API..."); // NOI18N
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public static GenerateApiAction getInstance() {
		return INSTANCE;
	}

}
