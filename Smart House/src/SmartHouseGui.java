
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SmartHouseGui extends JFrame {

    private final JPanel cards;
    private final JPanel p_rooms;
    private JScrollPane scrollPane;
    private final RoomPanel p_kitchen, p_liv_room, p_bathroom, p_gym;
    private final RoomPanel[] p_bedrooms;
    private final int n_bedrooms = 2, n_rooms = 6;

    private final String[] k_devices = {"Light", "Microwave", "Oven", "Fridge", "Alarm"};
    private final String[] l_devices = {"Light", "TV", "PS5", "Home Cinema", "Alarm"};
    private final String[] b_devices = {"Light", "Heater", "Jac", "Alarm"};
    private final String[] g_devices = {"Light", "Alarm", "A/C"};
    private final String[] b1_devices = {"Light", "TV", "A/C", "Alarm"};
    private final String[] b2_devices = {"Light", "A/C", "Alarm"};

    private final JButton b_kitchen, b_liv_room, b_bathroom, b_gym, b_backToMain;
    private final JButton[] b_bedrooms;

    private JMenuBar mb;

    // JMenu
    private JMenu x;

    private JMenuItem m1,m2;

    // Dimension stuff for UI
    int alpha = 100;
    int xLimit = 750, yLimit = 475;

    // Widths & Heights of rooms
    int gymWidth = 268, gymHeight = 206;
    int livWidth = 350, livHeight = 228;
    int bed0Width = 288, bed0Height = 159;
    int hallWidth = 354, hallHeight = 50;
    int kitWidth = 207, kitHeight = 228;
    int bed1Width = 177, bed1Height = 228;
    int[] bathXVertices = {558, 733, 733, 622, 622, 558};
    int[] bathYVertices = {0, 0, gymHeight, gymHeight, 161, 161};
    int nPoints = bathXVertices.length;

    HousePanel housePanel = new HousePanel();
    MousePos ms = new MousePos();

    public SmartHouseGui(){
        super("SmartHouse");

        mb = new JMenuBar();
        x = new JMenu("Menu");

        m1 = new JMenuItem("Help Greek");
        m2 = new JMenuItem("Help English");
        x.add(m1);
        x.add(m2);
        mb.add(x);
        setJMenuBar(mb);

        m1.addActionListener(
                e -> {
                    JFrame fr= new JFrame();
                    fr.setSize(400,650);
                    JLabel label1 = new JLabel();


                    label1.setText("<html>Βοήθεια<br/>" +
                            "=========================<br/>" +
                            "Ο χειρισμός αυτής της εφαρμογής είναι αρκετά εύκολος<br/>" +
                            "και αναλύεται λεπτομερώς παρακάτω.<br/>" +
                            "<br/>" +
                            "• Aρχικά μόλις γίνει εκκίνηση της εφαρμογής ο χρήστης<br/>" +
                            "τοποθετείται στο σημείο εκκίνησης το οποίο είναι<br/>" +
                            "μια πιστή αναπαράσταση των δωματίων και χώρων του σπιτιού.<br/>" +
                            "<br/>" +
                            "• Ο χρήστης μπορεί να μεταβεί στο δωμάτιο που επιθυμεί <br/>" +
                            "κάνοντας αριστερό κλικ στο συγκεκριμένο δωμάτιο.Στα δωμάτια <br/>" +
                            "αναγράφεται το όνομα τους και για διευκόλυνση του χρήστη<br/>" +
                            "αναπαρίστανται με διαφορετικό χρώμα- τα χρώματα είναι<br/>" +
                            "κατάλληλα για ανθρώπους με αχρωματοψία.<br/>" +
                            "<br/>" +
                            "• Εφόσον ο χρήστης βρίσκεται στο δωμάτιο που επιθυμεί<br/>" +
                            "μπορεί να χειριστεί τις συσκευές που υποστηρίζει το σύστημα<br/>" +
                            "οι οποίες βρίσκονται στην δεξιά μεριά του παραθύρου.Ο <br/>" +
                            "χρήστης μπορεί να ενεργοποιήσει ή να απενεργοποιήσει <br/>" +
                            "αυτές τις συσκευές με αριστερό κλικ.Υπάρχουν σε <br/>" +
                            "κάθε συσκευή οπτικά στοιχεία που γνωστοποιούν<br/>" +
                            "στον χρήστη εάν η εν λόγω συσκευή λειτουργεί ή όχι.<br/>" +
                            "<br/>" +
                            "• Στο κέντρο του παραθύρου υπάρχει μια εικόνα του δωματίου <br/>" +
                            "που υπενθυμίζει και στο χρήστη που βρίσκεται.<br/>" +
                            "<br/>" +
                            "• Εφόσον ο χρήστης θέλει να αποχωρήσει από το δωμάτιο<br/>" +
                            "που βρίσκεται, στην αριστερή μεριά του παραθύρου υπάρχουν<br/>" +
                            "όλα τα δωμάτια του σπιτιού. Ο χρήστης μπορεί να μεταβεί σε<br/>" +
                            "όποιο από αυτά επιθυμεί με αριστερό κλικ πάνω στο αντίστοιχο <br/>" +
                            "κουτάκι. Εναλλακτικά, μπορεί μέσω του 'House Layout' να <br/>" +
                            "μεταφερθεί στην αρχική πρόσοψη του σπιτιού.<br/>" +
                            "<br/>" +
                            "• Εάν ο χρήστης επιθυμεί να τερματίσει την εφαρμογή τότε<br/>" +
                            "μπορεί να κάνει αριστερό κλικ στο 'X' που βρίσκεται<br/>" +
                            "στην πάνω δεξιά μεριά του παραθύρου.<br/>" +
                            "<br/></html>");

                    fr.add(label1);
                    fr.setVisible(true);

                }
        );

        m2.addActionListener(
                e->{
                    JFrame fr= new JFrame();
                    fr.setSize(400,650);
                    JLabel label1 = new JLabel();


                    label1.setText("<html>Help<br/>" +
                            "=========================<br/>" +
                            "Using this application is vey easy and is explained<br/>" +
                            "in detail with the following instructions.<br/>" +
                            "<br/>" +
                            "• Initially as soon as the user launches the application<br/>" +
                            "they are placed at the starting point which is<br/>" +
                            "a faithful representation of the rooms and spaces of the house.<br/>" +
                            "<br/>" +
                            "• The user can go to the room they want<br/>" +
                            "by left-clicking on the specific room. Τhe name of each room<br/>" +
                            "is displayed on top of it and for the convenience of the user<br/>" +
                            "they are represented in a different color - the colours<br/>" +
                            "are suitable for colour-blind individuals.<br/>" +
                            "<br/>" +
                            "• If the user is in the room they want they<br/>" +
                            "can operate the devices supported by the system<br/>" +
                            "which are located on the right side of the window.The<br/>" +
                            "user can enable or disable<br/>" +
                            "these devices by left-clicking them.<br/>" +
                            "Each device has visual cues that communicate<br/>" +
                            "to the user whether that device is working or not.<br/>" +
                            "<br/>" +
                            "• In the center of the window there is a picture of the room<br/>" +
                            "which also reminds the user where they are.<br/>" +
                            "<br/>" +
                            "• If the user wants to leave the room<br/>" +
                            "they are in, on the left side of the window there are<br/>" +
                            "all the rooms of the house. The user can go to<br/>" +
                            "whichever one they wish by left-clicking on the corresponding<br/>" +
                            "box. Alternatively, by clicking on 'House Layout' they can be<br/>" +
                            "transferred to the original facade of the house.<br/>" +
                            "<br/>" +
                            "• If the user wishes to terminate the application then<br/>" +
                            "they can left-click on the 'X' located<br/>" +
                            "at the top right of the window.</html>");

                    fr.add(label1);
                    fr.setVisible(true);
                }
        );

        //JLabel img_label = new JLabel(new ImageIcon("resources/house.png"));
        JLabel img_label = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("house.png")));

        housePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0,0));
        housePanel.add(img_label);

        // Cards for card panel
        cards = new JPanel(new CardLayout());
        p_rooms = new JPanel();
        p_kitchen = new RoomPanel(k_devices, "Kitchen");
        p_liv_room = new RoomPanel(l_devices, "Living_Room");
        p_bathroom = new RoomPanel(b_devices, "Bathroom");
        p_gym = new RoomPanel(g_devices, "Gym");
        p_bedrooms = new RoomPanel[n_bedrooms];
        for (int i = 0; i < n_bedrooms; i++)
            p_bedrooms[i] = new RoomPanel(i == 0 ? b1_devices : b2_devices, "Bedroom"+i);

        // Buttons to access each room
        b_backToMain = new JButton("House Layout");
        b_kitchen = new JButton("Kitchen");
        b_liv_room = new JButton("Living Room");
        b_bathroom = new JButton("Bathroom");
        b_gym = new JButton("Gym");
        b_bedrooms = new JButton[n_bedrooms];
        for (int i = 0; i < n_bedrooms; i++)
            b_bedrooms[i] = new JButton("Bedroom " + i);

        // Layout of the panel with the buttons for each room
        p_rooms.setLayout(new GridLayout(n_rooms+1, 1));//p_rooms.setLayout(new GridLayout(3, 2));
        p_rooms.setBackground(new Color(0, 0, 0));
        GridLayout gl = (GridLayout) p_rooms.getLayout();
        gl.setVgap(2);



        p_rooms.add(b_backToMain);
        p_rooms.add(b_kitchen);
        p_rooms.add(b_liv_room);
        p_rooms.add(b_bathroom);
        p_rooms.add(b_gym);
        for (int i = 0; i < n_bedrooms; i++)
            p_rooms.add(b_bedrooms[i]);

        // button colors
        for (Component btn : p_rooms.getComponents()) {
            if (btn instanceof JButton) {
                btn.setPreferredSize(new Dimension(150, 50));
                btn.setBackground(new Color(0, 196, 222));
                btn.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}

                    @Override
                    public void mousePressed(MouseEvent e) {}

                    @Override
                    public void mouseReleased(MouseEvent e) {}

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        btn.setBackground(Color.CYAN);
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        btn.setBackground(new Color(0, 196, 222));
                    }
                });
            }
        }

        // Adding the cards to the controller panel
        cards.add(housePanel, "top_view");
        cards.add(p_kitchen, "kitchen");
        cards.add(p_liv_room, "living");
        cards.add(p_bathroom, "bathroom");
        cards.add(p_gym, "gym");
        for (int i = 0; i < n_bedrooms; i++)
            cards.add(p_bedrooms[i], "bedroom" + i);

        this.add(cards, BorderLayout.CENTER);

        // Listeners
        housePanel.addMouseMotionListener(ms);
        housePanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1 &&
                        ms.getMx() > 0 && ms.getMx() <= gymWidth &&
                        ms.getMy() > 0 && ms.getMy() <= gymHeight){
                    System.out.println("Gym");
                    showCard("gym");
                }

                if (e.getButton() == MouseEvent.BUTTON1 &&
                        ms.getMx() > 0 && ms.getMx() <= livWidth &&
                        ms.getMy() > gymHeight && ms.getMy() <= yLimit) {
                    System.out.println("Living Room");
                    showCard("living");
                }

                if (e.getButton() == MouseEvent.BUTTON1 &&
                        ms.getMx() >= gymWidth && ms.getMx() <= gymWidth+bed0Width &&
                        ms.getMy() > 0 && ms.getMy() <= bed0Height) {
                    System.out.println("Bedroom 0");
                    showCard("bedroom"+0);
                }

                if (e.getButton() == MouseEvent.BUTTON1 &&
                        (ms.getMx() >= gymWidth+bed0Width && ms.getMx() < xLimit &&
                                ms.getMy() > 0 && ms.getMy() < bed0Height) ||
                        (ms.getMx() >= gymWidth+hallWidth && ms.getMx() < xLimit &&
                                ms.getMy() >= bed0Height && ms.getMy() <= gymHeight)) {
                    System.out.println("Bathroom");
                    showCard("bathroom");
                }

                if (e.getButton() == MouseEvent.BUTTON1 &&
                        ms.getMx() >= livWidth && ms.getMx() < livWidth+kitWidth &&
                        ms.getMy() >= gymHeight && ms.getMy() <= yLimit) {
                    System.out.println("Kitchen");
                    showCard("kitchen");
                }

                if (e.getButton() == MouseEvent.BUTTON1 &&
                        ms.getMx() >= livWidth+kitWidth && ms.getMx() <= xLimit &&
                        ms.getMy() >= gymHeight && ms.getMy() <= yLimit) {
                    System.out.println("Bedroom 1");
                    showCard("bedroom"+1);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        // Action listeners for room buttons
        b_backToMain.addActionListener((e) -> {
            if (e.getSource() == b_backToMain)
                showCard("top_view");
        });

        b_kitchen.addActionListener((e) -> {
            if (e.getSource() == b_kitchen)
                showCard("kitchen");
        });

        b_liv_room.addActionListener((e) -> {
            if (e.getSource() == b_liv_room)
                showCard("living");
        });

        b_bathroom.addActionListener((e) -> {
            if (e.getSource() == b_bathroom)
                showCard("bathroom");
        });

        b_gym.addActionListener((e) -> {
            if (e.getSource() == b_gym)
                showCard("gym");
        });

        b_bedrooms[0].addActionListener((e) -> {
            if (e.getSource() == b_bedrooms[0])
                showCard("bedroom"+0);
        });

        b_bedrooms[1].addActionListener((e) -> {
            if (e.getSource() == b_bedrooms[1])
                showCard("bedroom"+1);
        });
    }

    // change card a.k.a. current panel
    private void showCard(String constr) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, constr);
        if (!constr.equals("top_view"))
            this.add(p_rooms, BorderLayout.LINE_START);//cards.add(p_rooms, "rooms");
        else
            this.remove(p_rooms);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocation(this.getLocation());
    }

    public class HousePanel extends JPanel {

        protected void paintChildren(Graphics g){
            super.paintChildren(g);
            Graphics2D g2d = (Graphics2D) g;

            // Borders
            g2d.setColor(Color.BLACK);
            // Gym
            g2d.drawRect(0, 0, gymWidth, gymHeight);
            // Living Room
            g2d.drawRect(0,gymHeight+1, livWidth, livHeight);
            // Bedroom 0
            g2d.drawRect(gymWidth, 0, bed0Width, bed0Height);
            // Bathroom
            Polygon bathPolygon = new Polygon(bathXVertices, bathYVertices, nPoints);
            g2d.drawPolygon(bathPolygon);
            // Hall
            // Kitchen
            g2d.drawRect(livWidth+1, gymHeight+1, kitWidth, kitHeight);
            // Bedroom 1
            g2d.drawRect(livWidth+kitWidth, gymHeight+1, bed1Width, bed1Height);

            // Colors
            // Gym
            g2d.setColor(new Color(0,255,255, alpha));
            g2d.fillRect(0, 0, gymWidth, gymHeight);
            // Living Room
            g2d.setColor(new Color(255, 180,255, alpha));
            g2d.fillRect(0,gymHeight+1, livWidth, livHeight);
            // Bedroom 0
            g2d.setColor(new Color(87, 87, 87, alpha));
            g2d.fillRect(gymWidth, 0, bed0Width, bed0Height);
            // Bathroom
            g2d.setColor(new Color(0,255,0, alpha));
            g2d.fillPolygon(bathPolygon);
            // Hall
            //g.setColor(new Color(150,150,150, alpha));
            //g.fillRect(251, 150, 350, 50);
            // Kitchen
            g2d.setColor(new Color(0,0,255, alpha));
            g2d.fillRect(livWidth+1, gymHeight+1, kitWidth, kitHeight);
            // Bedroom 1
            g2d.setColor(new Color(255,0,255, alpha));
            g2d.fillRect(livWidth+kitWidth, gymHeight+1, bed1Width, bed1Height);

            // Selection
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(new Color(255, 0,0, 255));
            // Gym
            if (ms.getMx() > 0 && ms.getMx() <= gymWidth &&
                    ms.getMy() > 0 && ms.getMy() <= gymHeight) {
                g2d.drawRect(0, 0, gymWidth, gymHeight);
            }

            // Living Room
            if (ms.getMx() > 0 && ms.getMx() <= livWidth &&
                    ms.getMy() > gymHeight && ms.getMy() <= yLimit) {
                g2d.drawRect(0,gymHeight+1, livWidth, livHeight);
            }

            // Bedroom 0
            if (ms.getMx() >= gymWidth && ms.getMx() <= gymWidth+bed0Width &&
                    ms.getMy() > 0 && ms.getMy() <= bed0Height) {
                g2d.drawRect(gymWidth, 0, bed0Width, bed0Height);
            }

            // Bathroom
            if ((ms.getMx() >= gymWidth+bed0Width && ms.getMx() < xLimit &&
                    ms.getMy() > 0 && ms.getMy() < bed0Height) ||
                    (ms.getMx() >= gymWidth+hallWidth && ms.getMx() < xLimit &&
                            ms.getMy() >= bed0Height && ms.getMy() <= gymHeight)) {
                g2d.drawPolygon(bathPolygon);
            }

            // Kitchen
            if (ms.getMx() >= livWidth && ms.getMx() < livWidth+kitWidth &&
                    ms.getMy() >= gymHeight && ms.getMy() <= yLimit) {
                g2d.drawRect(livWidth+1, gymHeight+1, kitWidth, kitHeight);

            }

            // Bedroom 1
            if (ms.getMx() >= livWidth+kitWidth && ms.getMx() <= xLimit &&
                    ms.getMy() >= gymHeight && ms.getMy() <= yLimit) {
                g2d.drawRect(livWidth+kitWidth, gymHeight+1, bed1Width, bed1Height);
            }


            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString("Gym",gymWidth/2-25,30);
            g2d.drawString("Bedroom",gymWidth+bed0Width/2-35, 30);
            g2d.drawString("Bathroom",gymWidth+bed0Width+bed1Width/2-45,30);

            g2d.drawString("Living room",livWidth/2-60,gymHeight+30);
            g2d.drawString("Kitchen",livWidth+kitWidth/2-35,gymHeight+30);
            g2d.drawString("Bedroom",livWidth+kitWidth+bed1Width/2-45,gymHeight+30);

        }

    }
}
