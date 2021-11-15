import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;

public class FenetreOublieMDP extends JFrame{
	
	private static final long serialVersionUID = 5983062096580235299L;
	
	private static FenetreOublieMDP instance;
	private JPasswordField pwdNouveauMDP;
	private JPasswordField pwdNouveauMDPComfirm;
	private JTextField txtIdentifiant;
	
	private FenetreOublieMDP() {
		super("Mot de passe oublié - B'ook la bibliothèque 2.0");
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
		this.setSize(600, 500);
		
		JPanel contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		
		JLabel lblOublieMDP = new JLabel("MOT DE PASSE OUBLI\u00C9");
		lblOublieMDP.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblOublieMDP.setBounds(68, 32, 464, 47);
		getContentPane().add(lblOublieMDP);
		
		JLabel lbNouveauMDP = new JLabel("Nouveau mot de passe :");
		lbNouveauMDP.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNouveauMDP.setBounds(88, 220, 188, 24);
		getContentPane().add(lbNouveauMDP);
		
		pwdNouveauMDP = new JPasswordField();
		pwdNouveauMDP.setBounds(295, 220, 200, 28);
		getContentPane().add(pwdNouveauMDP);
		
		JLabel lbNouveauMDPConfirm = new JLabel("Confirmer nouveau mot de passe :");
		lbNouveauMDPConfirm.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbNouveauMDPConfirm.setBounds(88, 260, 269, 24);
		getContentPane().add(lbNouveauMDPConfirm);
		
		pwdNouveauMDPComfirm = new JPasswordField();
		pwdNouveauMDPComfirm.setBounds(376, 260, 200, 28);
		getContentPane().add(pwdNouveauMDPComfirm);
		
		JButton btnComfirmer = new JButton("Comfirmer");
		btnComfirmer.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnComfirmer.setBounds(164, 396, 273, 46);
		getContentPane().add(btnComfirmer);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 90, 600, 20);
		getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 373, 600, 20);
		getContentPane().add(separator_1);
		
		JLabel lbIdentifiant = new JLabel("Identifiant :");
		lbIdentifiant.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lbIdentifiant.setBounds(88, 180, 91, 24);
		getContentPane().add(lbIdentifiant);
		
		txtIdentifiant = new JTextField();
		txtIdentifiant.setBounds(198, 180, 200, 28);
		getContentPane().add(txtIdentifiant);
		txtIdentifiant.setColumns(10);
	}
	
	public static FenetreOublieMDP getInstance() {
        if( instance == null ) {
            instance = new FenetreOublieMDP();
        }
         return instance;
   }
	
	public static void killInstance() {
		if (instance != null)
			instance.setVisible(false);
		instance = null;
	}

}
