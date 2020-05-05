package com.gearsetup.ui;

//import com.gearsetup.Phase;

import com.gearsetup.GearSetupPlugin;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.util.ImageUtil;

import javax.inject.Singleton;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

@Slf4j
@Singleton
public class GearSetupPanel extends PluginPanel {
    private final GearSetupPlugin plugin;

    private static final ImageIcon RESET_ICON;
    private static final ImageIcon RESET_HOVER_ICON;

    static {
        final BufferedImage addIcon = ImageUtil.getResourceStreamFromClass(GearSetupPlugin.class, "/ui/reset_icon.png");
        RESET_ICON = new ImageIcon(addIcon);
        RESET_HOVER_ICON = new ImageIcon(ImageUtil.alphaOffset(addIcon, 0.53f));
    }

    private final JLabel title = new JLabel();
    private final JButton reset = new JButton(RESET_ICON);

    public GearSetupPanel(GearSetupPlugin plugin) {
        this.plugin = plugin;

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.setBorder(new EmptyBorder(1, 0, 10, 0));

        title.setText("Gear Setup");
        title.setForeground(Color.WHITE);

        northPanel.add(title, BorderLayout.WEST);
        northPanel.add(reset, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(ColorScheme.DARK_GRAY_COLOR);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;


        reset.setToolTipText("Gear Setup");
        reset.setRolloverIcon(RESET_HOVER_ICON);
        reset.addActionListener(l -> this.onReset());

        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }

    private void onReset() {
        log.debug("Reset");
    }

    private void update() {
        SwingUtilities.invokeLater(() -> updatePanel());
    }

    private void updatePanel() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 1;
        constraints.gridx = 0;
        constraints.gridy = 0;

        JPanel ip = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.VERTICAL;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;


        repaint();
        revalidate();
    }
}