package org.vanvu.psi.view;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.psi.PsiElement;
import com.intellij.ui.components.JBScrollPane;
import org.vanvu.psi.controller.project.PsiProjectService;
import org.vanvu.psi.model.PsiTreeModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class PsiPanel extends JPanel {
    private static final Logger LOG = Logger.getInstance(PsiPanel.class);
    private String actionTitle;
    private PsiTree tree;
    private PsiTreeModel model;
    private PsiElement rootElement; // The root element of the tree
    private PsiElement selectedElement; // The currently selected element in the tree
    private PropertySheetPanel propertyPanel;
    private final Project project;
    private ToolWindow toolWindow;
    private JSplitPane splitPane;
    //    private final TreeSelectionListener treeSelectionListener;
//    private final EditorCaretMover caretMover;
//    private final EditorPsiElementHighlighter highlighter;
    private final PsiProjectService projectComponent;
    public PsiPanel(PsiProjectService projectCompo) {
        projectComponent = projectCompo;
        project = projectComponent.getProject();
//        caretMover = new EditorCaretMover(this.projectComponent.getProject());
//        highlighter = new EditorPsiElementHighlighter(_project);
//        model = new PsiTreeModel(this.projectComponent);
//        treeSelectionListener = new TreeSelectionListener();

        buildGUI();
    }
    private void buildGUI()
    {
        setLayout(new BorderLayout());

        tree = new PsiTree(model);
//        tree.getSelectionModel().addTreeSelectionListener(_treeSelectionListener);

        ActionMap actionMap = tree.getActionMap();
        actionMap.put("EditSource", new AbstractAction("EditSource") {
            public void actionPerformed(ActionEvent e)
            {
                debug("key typed " + e);
//                if (getSelectedElement() == null) return;
//                Editor editor = _caretMover.openInEditor(getSelectedElement());
//                selectElementAtCaret(editor, TREE_SELECTION_CHANGED);
//                editor.getContentComponent().requestFocus();
            }
        });
        InputMap inputMap = tree.getInputMap();
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, true), "EditSource");

        propertyPanel = new PropertySheetPanel();

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JBScrollPane(tree), propertyPanel) {
            public void setDividerLocation(int location)
            {
                debug("Divider location changed to " + location + " component below " + (getRightComponent().isVisible() ? "visible" : "not visible"));
                if (getRightComponent().isVisible())
                    projectComponent.setSplitDividerLocation(location);
                super.setDividerLocation(location);
            }
        };
        splitPane.setDividerLocation(projectComponent.getSplitDividerLocation());
        add(splitPane);
    }
    private ToolWindow getToolWindow()
    {
        return toolWindow;
    }

    public void setToolWindow(ToolWindow tWindow)
    {
        toolWindow = tWindow;
    }
    private static void debug(String message)
    {
        if (LOG.isDebugEnabled())
        {
            LOG.debug(message);
        }
    }

}
