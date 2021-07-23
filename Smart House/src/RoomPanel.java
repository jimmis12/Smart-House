import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;


public class RoomPanel extends JSplitPane {

    // Left & right panels of the JSplitPane
    private final JPanel leftPanel, rightPanel;
    // panel contained within the rightPanel
    private final JPanel settings;





    // Button dimensions
    private final int btnWidth = 70, btnHeight = 70;
    // Size constraints for components in the MigLayout
    private final String conWidth = "w "+btnWidth+"!", conHeight = "h "+btnHeight+"!";

    // Left & right panel widths
    private final int rightPanelWidth = btnWidth*2+2;
    private final int leftPanelWidth = 400;

    // Left panel label img
    private final JLabel imgLabel;

    // Constructor receives an argument with all the device names for a room
    public RoomPanel(String[] devices, String room) {
        leftPanel = new JPanel(new BorderLayout()); // img panel
        rightPanel = new JPanel(new BorderLayout()); // smart panel


       // ImageIcon roomImg = new ImageIcon("resources/"+room.toLowerCase()+"_off.png");
        ImageIcon roomImg = new ImageIcon(getClass().getClassLoader().getResource(room.toLowerCase()+"_off.png"));

        imgLabel = new JLabel(roomImg);
        leftPanel.add(imgLabel, BorderLayout.CENTER);

        // Left & right panel preferred sizes
        leftPanel.setPreferredSize(new Dimension(leftPanelWidth, 500));
        rightPanel.setPreferredSize(new Dimension(rightPanelWidth, 500));

        // Layout of the settings panel (inside of the right panel)
        settings = new JPanel(new MigLayout("align c c"));
        settings.setBackground(new Color(0, 250, 250));
        int i = 1;
        // For every device name
        for (String d : devices) {
            if (i%2 != 0) { // if on the 1st column
                if (i == devices.length) { // If this is the last device
                    // Add it's label...
                    settings.add(new JLabel(d), "align c, wrap, spanx 2");
                    // and it's button.
                    settings.add(new JButton(devices[i - 1].replace('/', '_')), "align c c, spanx 2, growx,"+conWidth+","+conHeight);
                }
                else // Otherwise just the label. ***
                    settings.add(new JLabel(d), "align c");
            }
            else { // else if on the 2nd column ***
                // Add the label
                settings.add(new JLabel(d), "wrap, align c");
                // and the buttons that go below the last and this label
                settings.add(new JButton(devices[i-2].replace('/', '_')), conWidth+","+conHeight);
                settings.add(new JButton(devices[i-1].replace('/', '_')), "wrap,"+conWidth+","+conHeight);
            }
            i++;
            /*  the else statements with *** in their comment
                work with each other */
        }
        rightPanel.add(settings);

        // Custom divider
        this.setUI(new BasicSplitPaneUI()
        {
            @Override
            public BasicSplitPaneDivider createDefaultDivider() {
                return new BasicSplitPaneDivider(this) {
                    public void setBorder(Border b) {}

                    @Override
                    public void paint(Graphics g)
                    {
                        g.setColor(new Color(200, 255, 255));
                        g.fillRect(0, 0, getSize().width, getSize().height);
                        super.paint(g);
                    }
                };
            }
        });
        this.setBorder(BorderFactory.createMatteBorder(2,0,2,2, Color.BLACK));
        this.setDividerLocation(leftPanelWidth);
        this.setEnabled(false);
        this.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
        this.setLeftComponent(leftPanel);
        this.setRightComponent(rightPanel);
        this.resetToPreferredSizes();

        // For each component in the current settings panel...
        for (Component c : settings.getComponents()) {
            // If the component is an instance of a JButton...
            if (c instanceof JButton) {
                JButton btn = (JButton) c; // Reference to the component
                String btnTxt = btn.getText().toLowerCase(); // Reference to button's text

                // Button icons

                ImageIcon icon_state0 = new ImageIcon(getClass().getClassLoader().getResource(btnTxt+"_state0.png"));
                //getClass().getClassLoader().getResource(btnTxt+"_state0.png");
                ImageIcon icon_state1 = new ImageIcon(getClass().getClassLoader().getResource(btnTxt+"_state1.png"));
                // Room lights on/off

                ImageIcon lights_on =  new ImageIcon(getClass().getClassLoader().getResource(room.toLowerCase()+"_on.png"));
                ImageIcon lights_off = new ImageIcon(getClass().getClassLoader().getResource(room.toLowerCase()+"_off.png"));




                // ...removing text & setting the default icon...
                btn.setText(""); btn.setIcon(icon_state0);
                // ...adding action listeners to control the icon being shown for each button.
                btn.addActionListener(e -> {
                    if (e.getSource() instanceof JButton) {
                        if (btn.getIcon() == icon_state0) {
                            btn.setIcon(icon_state1);
                            System.out.println(icon_state1);
                            if (btnTxt.equals("light"))
                                imgLabel.setIcon(lights_on);
                        }
                        else {
                            btn.setIcon(icon_state0);
                            if (btnTxt.equals("light"))
                                imgLabel.setIcon(lights_off);
                        }


                    }
                });
            }
        }
    }
}
