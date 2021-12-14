import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import net.proteanit.sql.DbUtils;


public class FenetreMesReservationsEmprunts extends JFrame{

	private static final long serialVersionUID = 7601905195101906770L;

	private static FenetreMesReservationsEmprunts instance;

	private Client client;
	private JTable tableLivre;
	private JTable tableSalle;
	private JTable tablePC;


	/**
     * Constructeur de l'instance FenetreMesReservationsEmprunts, initialise la fenetre (design, taille , bouton etc...) 
     * @param client
     */
	private FenetreMesReservationsEmprunts(Client client) {
		super("Mes reservations et emprunts - B'ook la bibliotheque 2.0");

		this.client = client;

		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				killInstance();
				dispose();
			}
		});
		this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

		//definit sa taille
		this.setSize(800, 800);

		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);


		tableLivre = new JTable();
		tableLivre.setBounds(0, 0, 610, 150);
		JScrollPane scrollPaneLivre = new JScrollPane(tableLivre);
		contentPane.add(scrollPaneLivre);
		scrollPaneLivre.setBounds(36, 100, 610, 150);
		tableLivre.setDefaultEditor(Object.class, null);
		tableLivre.getTableHeader().setResizingAllowed(false);
		tableLivre.getTableHeader().setReorderingAllowed(false);
		afficherTableauLivre();

		JLabel lbLivre = new JLabel("Livre :");
		lbLivre.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbLivre.setBounds(50, 40, 150, 30);
		contentPane.add(lbLivre);

		tableSalle = new JTable();
		tableSalle.setBounds(0, 0, 610, 150);
		JScrollPane scrollPaneSalle = new JScrollPane(tableSalle);
		contentPane.add(scrollPaneSalle);
		scrollPaneSalle.setBounds(36, 320, 610, 150);
		tableSalle.setDefaultEditor(Object.class, null);
		tableSalle.getTableHeader().setResizingAllowed(false);
		tableSalle.getTableHeader().setReorderingAllowed(false);
		afficherTableauSalle();

		JLabel lbSalle = new JLabel("Salle :");
		lbSalle.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbSalle.setBounds(50, 260, 150, 30);
		contentPane.add(lbSalle);

		tablePC = new JTable();
		tablePC.setBounds(0, 0, 448, 322);
		JScrollPane scrollPanePC = new JScrollPane(tablePC);
		contentPane.add(scrollPanePC);
		scrollPanePC.setBounds(36, 540, 610, 150);
		tablePC.setDefaultEditor(Object.class, null);
		tablePC.getTableHeader().setResizingAllowed(false);
		tablePC.getTableHeader().setReorderingAllowed(false);
		afficherTableauPc();

		JLabel lbPC = new JLabel("PC :");
		lbPC.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lbPC.setBounds(50, 480, 150, 30);
		contentPane.add(lbPC);
	}

	/**
     * Renvoie l'instance de la FenetreMesReservationsEmprunts si elle existe ou en crée une avec le paramètre client si elle n'existe pas
     * @param client
     * @return instance du singleton FenetreMesReservationsEmprunts
     */
	public static FenetreMesReservationsEmprunts getInstance(Client client) {
		if( instance == null ) {
			instance = new FenetreMesReservationsEmprunts(client);
		}
		return instance;
	}

	/**
     * Supprime l'instance de la FenetreMesReservationsEmprunts et ferme la fenetre à l'ecran
     */
	public static void killInstance() {
		if (instance != null)
			instance.setVisible(false);
		instance = null;
	}

	/**
	 * Renvoie l'instance de la FenetreMesReservationsEmprunts si elle existe
	 * @return instance de la FenetreMesReservationsEmprunts
	 * @throws Exception l'instance n'existe pas
	 */
	public static FenetreMesReservationsEmprunts getInstance() throws Exception{
		if( instance == null ) {
			throw new Exception();
		}
		return instance;
	}


	/**
	 * Affiche le tableau des livres emprunté par le client
	 */
	public void afficherTableauLivre() {

		try {
			PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_Livre, Titre, Auteur, Sujet FROM livre WHERE ID_client = " + client.getID());
			ResultSet rs = ps.executeQuery();
			int size = 0;
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
			if (size > 0) {
				tableLivre.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel model = (DefaultTableModel)tableLivre.getModel();
				model.addColumn("Rendre");
				tableLivre.setRowHeight(30);
				tableLivre.getColumnModel().getColumn(0).setHeaderValue("ID");
				tableLivre.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableLivre.getColumnModel().getColumn(1).setPreferredWidth(250);
				tableLivre.getColumnModel().getColumn(2).setPreferredWidth(120);
				tableLivre.getColumnModel().getColumn(3).setPreferredWidth(65);
				tableLivre.getColumnModel().getColumn(4).setPreferredWidth(114);
				tableLivre.getColumn("Rendre").setCellRenderer(new ButtonRendererRendreLivre());
				tableLivre.getColumn("Rendre").setCellEditor(new ButtonEditorRendreLivre());
			}else {
				tableLivre.setModel(DbUtils.resultSetToTableModel(rs));
				tableLivre.getColumnModel().getColumn(0).setHeaderValue("ID");
				DefaultTableModel model = (DefaultTableModel) tableLivre.getModel();
				model.addRow(new Object[]{"Aucun resultat"});
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	/**
	 * Affiche le tableau des salles reservées par le client
	 */
	public void afficherTableauSalle() {
		try {
			PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_salle, Nombre_chaise, Nombre_table, Projecteur , Taille FROM salle WHERE ID_client = " + client.getID());
			ResultSet rs = ps.executeQuery();
			int size = 0;
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
			if (size > 0) {
				tableSalle.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel model = (DefaultTableModel) tableSalle.getModel();
				model.addColumn("Rendre");
				tableSalle.setRowHeight(30);
				tableSalle.getColumnModel().getColumn(0).setHeaderValue("ID");
				tableSalle.getColumnModel().getColumn(0).setPreferredWidth(40);
				tableSalle.getColumnModel().getColumn(1).setPreferredWidth(250);
				tableSalle.getColumnModel().getColumn(2).setPreferredWidth(120);
				tableSalle.getColumnModel().getColumn(3).setPreferredWidth(65);
				tableSalle.getColumnModel().getColumn(4).setPreferredWidth(48);
				tableSalle.getColumnModel().getColumn(5).setPreferredWidth(114);
				tableSalle.getColumn("Rendre").setCellRenderer(new ButtonRendererRendreSalle());
				tableSalle.getColumn("Rendre").setCellEditor(new ButtonEditorRendreSalle());
			}else{
				tableSalle.setModel(DbUtils.resultSetToTableModel(rs));
				tableSalle.getColumnModel().getColumn(0).setHeaderValue("ID");
				DefaultTableModel model = (DefaultTableModel) tableSalle.getModel();
				model.addRow(new Object[]{"Aucun resultat"});
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Affiche le tableau des Pc réservés par le client
	 */
	public void afficherTableauPc() {

		try {
			PreparedStatement ps = Bibliotheque.getInstance().getConnexion().prepareStatement("SELECT ID_pc, Marque, SN FROM pc WHERE ID_client = " + client.getID());
			ResultSet rs = ps.executeQuery();
			int size = 0;
			rs.last();
			size = rs.getRow();
			rs.beforeFirst();
			if (size > 0) {
				tablePC.setModel(DbUtils.resultSetToTableModel(rs));
				DefaultTableModel model = (DefaultTableModel) tablePC.getModel();
				model.addColumn("Rendre");
				tablePC.setRowHeight(30);
				tablePC.getColumnModel().getColumn(0).setHeaderValue("ID");
				tablePC.getColumnModel().getColumn(0).setPreferredWidth(40);
				tablePC.getColumnModel().getColumn(1).setPreferredWidth(100);
				tablePC.getColumnModel().getColumn(2).setPreferredWidth(150);
				tablePC.getColumnModel().getColumn(2).setPreferredWidth(114);
				tablePC.getColumn("Rendre").setCellRenderer(new ButtonRendererRendrePC());
				tablePC.getColumn("Rendre").setCellEditor(new ButtonEditorRendrePC());
			}else{
				tablePC.setModel(DbUtils.resultSetToTableModel(rs));
				tablePC.getColumnModel().getColumn(0).setHeaderValue("ID");
				DefaultTableModel model = (DefaultTableModel) tablePC.getModel();
				model.addRow(new Object[]{"Aucun resultat"});
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}


}

//Permet d'afficher les boutons pour rendre un livre dans le taleau des livres empruntés
class ButtonRendererRendreLivre extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = -3648916372261420234L;


	public ButtonRendererRendreLivre() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
												   boolean isSelected, boolean hasFocus, int row, int column) {
		setText("Rendre");
		return this;
	}
}

class ButtonEditorRendreLivre extends DefaultCellEditor {


	private static final long serialVersionUID = -5036678594718053972L;

	protected JButton button;

	public ButtonEditorRendreLivre() {
		super(new JCheckBox());
		button = new JButton();
		button.setOpaque(true);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
												 boolean isSelected, int row, int column) {
		button.setText("Rendre");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("row :" + row + " column : " + column);
				try {
					Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
					stmt.executeUpdate("UPDATE Livre SET ID_client = NULL, EstDisponible = 1 WHERE ID_livre ='" + table.getValueAt(row, 0) + "';");
					JOptionPane.showMessageDialog(new JButton(),"Le livre a bien ete rendu");
					FenetreMesReservationsEmprunts.getInstance().afficherTableauLivre();
					FenetreUtilisateur.getInstance().afficherTableauLivre();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JButton(),"Erreur livre non disponible");
					try {
						FenetreMesReservationsEmprunts.getInstance().afficherTableauLivre();
						FenetreUtilisateur.getInstance().afficherTableauLivre();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.getColumn("Rendre").setCellEditor(new ButtonEditorRendreLivre());
		return button;

	}
}


//Permet d'afficher les boutons pour rendre une salle dans le taleau des salles reservées
class ButtonRendererRendreSalle extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = 3215034570725023894L;

	public ButtonRendererRendreSalle() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
												   boolean isSelected, boolean hasFocus, int row, int column) {
		setText("Rendre");
		return this;
	}
}

class ButtonEditorRendreSalle extends DefaultCellEditor {


	private static final long serialVersionUID = -4512823222784361082L;

	protected JButton button;

	public ButtonEditorRendreSalle() {
		super(new JCheckBox());
		button = new JButton();
		button.setOpaque(true);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
												 boolean isSelected, int row, int column) {
		button.setText("Rendre");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("row :" + row + " column : " + column);
				try {
					Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
					stmt.executeUpdate("UPDATE salle SET ID_client = NULL, EstDisponible = 1 WHERE ID_salle ='" + table.getValueAt(row, 0) + "';");
					JOptionPane.showMessageDialog(new JButton(),"La salle a bien ete rendu");
					FenetreMesReservationsEmprunts.getInstance().afficherTableauSalle();
					FenetreUtilisateur.getInstance().afficherTableauSalle();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JButton(),"Erreur salle non disponible");
					try {
						FenetreMesReservationsEmprunts.getInstance().afficherTableauSalle();
						FenetreUtilisateur.getInstance().afficherTableauSalle();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.getColumn("Rendre").setCellEditor(new ButtonEditorRendreSalle());
		return button;

	}
}

//Permet d'afficher les boutons pour rendre un Pc dans le taleau des Pc reservés
class ButtonRendererRendrePC extends JButton implements TableCellRenderer {

	private static final long serialVersionUID = 3215034570725023894L;

	public ButtonRendererRendrePC() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
												   boolean isSelected, boolean hasFocus, int row, int column) {
		setText("Rendre");
		return this;
	}
}

class ButtonEditorRendrePC extends DefaultCellEditor {


	private static final long serialVersionUID = -4512823222784361082L;

	protected JButton button;

	public ButtonEditorRendrePC() {
		super(new JCheckBox());
		button = new JButton();
		button.setOpaque(true);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value,
												 boolean isSelected, int row, int column) {
		button.setText("Rendre");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("row :" + row + " column : " + column);
				try {
					Statement stmt = Bibliotheque.getInstance().getConnexion().createStatement();
					stmt.executeUpdate("UPDATE pc SET ID_client = NULL, EstDisponible = 1 WHERE ID_pc ='" + table.getValueAt(row, 0) + "';");
					JOptionPane.showMessageDialog(new JButton(),"Le pc a bien ete rendu");
					FenetreMesReservationsEmprunts.getInstance().afficherTableauPc();
					FenetreUtilisateur.getInstance().afficherTableauPc();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(new JButton(),"Erreur pc non disponible");
					try {
						FenetreMesReservationsEmprunts.getInstance().afficherTableauPc();
						FenetreUtilisateur.getInstance().afficherTableauPc();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		table.getColumn("Rendre").setCellEditor(new ButtonEditorRendrePC());
		return button;

	}
}
