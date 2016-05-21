package com.itexpertnepal.simpleinvoice.jsf.component;

import com.itexpertnepal.simpleinvoice.spring.jsf.annotation.SpringSessionScoped;
import java.io.Serializable;
import org.primefaces.component.accordionpanel.AccordionPanel;
import org.primefaces.event.TabChangeEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author binay
 */
@Component
@SpringSessionScoped
public class MenuBinder implements Serializable {

    private int tabIndex;

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public void onTabChange(TabChangeEvent event) {
        AccordionPanel panel = (AccordionPanel) event.getComponent().findComponent("main_menu_tab");
        if (panel != null) {
            setTabIndex(Integer.parseInt(panel.getActiveIndex()));
        }
    }

}
