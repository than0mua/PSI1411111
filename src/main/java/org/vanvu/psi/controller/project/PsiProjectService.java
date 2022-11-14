package org.vanvu.psi.controller.project;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowManager;
import com.intellij.ui.components.panels.HorizontalLayout;
import org.jdesktop.swingx.combobox.ListComboBoxModel;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.vanvu.psi.actions.PropertyToggleAction;
import org.vanvu.psi.view.PsiPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collection;

public class PsiProjectService implements PersistentStateComponent<PsiProjectService.State>,
        Disposable {
    @Override
    public void dispose() {

    }

    @Override
    public @Nullable PsiProjectService.State getState() {
        return null;
    }

    @Override
    public void loadState(@NotNull State state) {

    }
    private static final Logger LOG = Logger.getInstance(PsiProjectService.class);
    static class State {
        public boolean HIGHLIGHT = false;
        public boolean FILTER_WHITESPACE = false;
        public boolean SHOW_PROPERTIES = true;
        public int SPLIT_DIVIDER_POSITION = 300;
        public boolean AUTOSCROLL_TO_SOURCE = false;
        public boolean AUTOSCROLL_FROM_SOURCE = false;
    }
    private State myState = new State();

    private ComboBox myLanguagesComboBox;
    private final Project myProject;
    //    private EditorListener myEditorListener;
    private PsiPanel myPanel;
    private PsiProjectService(Project project) {
        myProject = project;
    }
    public void registerToolWindow() {
        ToolWindow toolWindow = getToolWindow();
        initToolWindow(toolWindow);
        toolWindow.setAvailable(true, null);
    }
    PsiPanel initToolWindow(@NotNull ToolWindow toolWindow)
    {
        myPanel = new PsiPanel(this);

        myPanel.addPropertyChangeListener("ancestor", it -> handleCurrentState());
        ActionManager actionManager = ActionManager.getInstance();

        DefaultActionGroup actionGroup = new DefaultActionGroup("PsiActionGroup", false);
        actionGroup.add(new PropertyToggleAction("Filter Whitespace",
                "Remove whitespace elements",
                AllIcons.Actions.Execute,
                this::isFilterWhitespace,
                this::setFilterWhitespace));
        actionGroup.add(new PropertyToggleAction("Highlight",
                "Highlight selected PSI element",
                AllIcons.Actions.Execute,
                this::isHighlighted,
                this::setHighlighted));
        actionGroup.add(new PropertyToggleAction("Highlight",
                "Highlight selected PSI element",
                AllIcons.Actions.Execute,
                this::isHighlighted,
                this::setHighlighted));
        actionGroup.add(new PropertyToggleAction("Highlight",
                "Highlight selected PSI element",
                AllIcons.Actions.Execute,
                this::isHighlighted,
                this::setHighlighted));
//        actionGroup.add(new PropertyToggleAction("Properties",
//                "Show PSI element properties",
//                AllIcons.General.Settings,
//                this::isShowProperties,
//                this::setShowProperties));
//        actionGroup.add(new PropertyToggleAction("Autoscroll to Source",
//                "Autoscroll to Source",
//                AllIcons.General.AutoscrollToSource,
//                this::isAutoScrollToSource,
//                this::setAutoScrollToSource));
//        actionGroup.add(new PropertyToggleAction("Autoscroll from Source",
//                "Autoscroll from Source111",
//                AllIcons.General.AutoscrollFromSource,
//                this::isAutoScrollFromSource,
//                this::setAutoScrollFromSource));

        ActionToolbar toolBar = actionManager.createActionToolbar("PsiActionToolbar", actionGroup, true);

        JPanel panel = new JPanel(new HorizontalLayout(0));
        panel.add(toolBar.getComponent());

        myLanguagesComboBox = new ComboBox();
        panel.add(myLanguagesComboBox);
//        updateLanguagesList(Collections.<Language>emptyList());
//
        myPanel.add(panel, BorderLayout.NORTH);
//        myPanel.add(toolWindow);

//        myEditorListener = new EditorListener(myPanel, myProject);

        return myPanel;
    }
    private void handleCurrentState()
    {
        if (myPanel == null)
            return;

//        if (myPanel.isDisplayable()) {
//            myEditorListener.start();
//            myPanel.selectElementAtCaret();
//        } else {
//            myEditorListener.stop();
//            myPanel.removeHighlighting();
//        }
    }
    public Project getProject()
    {
        return myProject;
    }
    private ToolWindow getToolWindow()
    {
        return ToolWindowManager.getInstance(myProject).getToolWindow("Psi");
    }
    public boolean isHighlighted()
    {
        return myState.HIGHLIGHT;
    }
    public void setHighlighted(boolean highlight)
    {
        debug("set highlight to " + highlight);
        myState.HIGHLIGHT = highlight;
//        myPanel.applyHighlighting();

    }
    private static void debug(String message)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(message);
        }
    }
    public boolean isFilterWhitespace()
    {
        return myState.FILTER_WHITESPACE;
    }

    public void setFilterWhitespace(boolean filterWhitespace)
    {
        myState.FILTER_WHITESPACE = filterWhitespace;
//        getPanel().applyWhitespaceFilter();
    }

    public boolean isShowProperties()
    {
        return myState.SHOW_PROPERTIES;
    }
    public int getSplitDividerLocation()
    {
        return myState.SPLIT_DIVIDER_POSITION;
    }

    public void setSplitDividerLocation(int location)
    {
        myState.SPLIT_DIVIDER_POSITION = location;
    }
    public static PsiProjectService getInstance(Project project) {
        return project.getService(PsiProjectService.class);
    }











    private final ItemListener myLanguagesComboBoxListener = new ItemListener() {
        @Override
        public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
//                myPanel.refreshRootElement();
//                myPanel.selectElementAtCaret();
            }
        }
    };






    public void unregisterToolWindow()
    {
//        if (myPanel != null) {
//            myPanel.removeHighlighting();
//            myPanel = null;
//        }
//
//        if (myEditorListener != null) {
//            myEditorListener.stop();
//            myEditorListener = null;
//        }
        getToolWindow().setAvailable(false, null);
    }




    public PsiPanel getPanel()
    {
        return myPanel;
    }







    public void setShowProperties(boolean showProperties)
    {
        myState.SHOW_PROPERTIES = showProperties;
//        getPanel().showProperties(showProperties);
    }


    public boolean isAutoScrollToSource()
    {
        return myState.AUTOSCROLL_TO_SOURCE;
    }

    public void setAutoScrollToSource(boolean isAutoScrollToSource)
    {
        debug("autoscrolltosource=" + isAutoScrollToSource);
        myState.AUTOSCROLL_TO_SOURCE = isAutoScrollToSource;
    }

    public boolean isAutoScrollFromSource()
    {
        return myState.AUTOSCROLL_FROM_SOURCE;
    }

    public void setAutoScrollFromSource(boolean isAutoScrollFromSource)
    {
        debug("autoscrollfromsource=" + isAutoScrollFromSource);
        myState.AUTOSCROLL_FROM_SOURCE = isAutoScrollFromSource;
    }



    @Nullable
    public Language getSelectedLanguage()
    {
        return (Language) myLanguagesComboBox.getSelectedItem();
    }

    public void updateLanguagesList(Collection<Language> languages)
    {
        Language selectedLanguage = getSelectedLanguage();

        myLanguagesComboBox.removeItemListener(myLanguagesComboBoxListener);

        //noinspection Since15
        myLanguagesComboBox.setModel(new ListComboBoxModel<>(new ArrayList<>(languages)));

        if (selectedLanguage != null && languages.contains(selectedLanguage))
        {
            myLanguagesComboBox.setSelectedItem(selectedLanguage);
        }

        if (languages.size() < 2) {
            myLanguagesComboBox.setVisible(false);
        } else {
            myLanguagesComboBox.setVisible(true);
        }

        myLanguagesComboBox.addItemListener(myLanguagesComboBoxListener);
    }

    public interface BooleanConsumer {
        void accept(boolean value);
    }
}
