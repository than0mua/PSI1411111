package org.vanvu.psi.view;

import com.intellij.openapi.diagnostic.Logger;

import javax.swing.*;
import java.awt.*;

public class PropertySheetPanel extends JPanel {
    private Object myTarget;
    private JTable myTable;

    private static final Logger LOG = Logger.getInstance(PropertySheetPanel.class);

    public PropertySheetPanel() {
        setLayout(new GridBagLayout());
    }
}
