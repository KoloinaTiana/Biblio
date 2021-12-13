import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



	public class FenetreInfoLivre extends JFrame{
	    private static final long serialVersionUID = 1956692087798942826L; // permet d'enlever un warning (pas important car on ne l'utilise pas)

	    private static FenetreInfoLivre instance;

		
            /**  initialise la fenetre (design, taille , bouton etc...)
	      * @param titre titre
	      * @param auteur auteur
	      * @param resume resume
	      * @param sujet sujet
              * @return  fenetre
              */
	    public FenetreInfoLivre(String titre, String auteur, String resume, String sujet) {
	    	super("Info livre - B'ook la bibliotheque 2.0");
			
	    	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter(){
				@Override
		        public void windowClosing(WindowEvent e) {
			    	killInstance();
			    	dispose();
			    }
			});
			this.setResizable(false);// empêche toutes modifications de la taille de la fenêtre

			// centralise la fenetre et definit sa taille
			this.setSize(500, 500);
			this.setLocationRelativeTo(null);

			JPanel contentPane = (JPanel) this.getContentPane();
			contentPane.setLayout(null);
			
			JLabel lbTitre = new JLabel("Titre : ");
			lbTitre.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbTitre.setSize(300, 20);
			lbTitre.setLocation(50, 50);
			contentPane.add(lbTitre);
			
			JLabel lbTitreTxt = new JLabel(titre);
			lbTitreTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbTitreTxt.setSize(300, 20);
			lbTitreTxt.setLocation(120, 50);
			contentPane.add(lbTitreTxt);
			
			JLabel lbAuteur = new JLabel("Auteur : ");
			lbAuteur.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbAuteur.setSize(300, 20);
			lbAuteur.setLocation(50, 100);
			contentPane.add(lbAuteur);
			
			JLabel lbAuteurTxt = new JLabel(auteur);
			lbAuteurTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbAuteurTxt.setSize(300, 20);
			lbAuteurTxt.setLocation(135, 100);
			contentPane.add(lbAuteurTxt);
			
			JLabel lbsujet = new JLabel("Sujet : ");
			lbsujet.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbsujet.setSize(300, 20);
			lbsujet.setLocation(50, 150);
			contentPane.add(lbsujet);
			
			JLabel lbsujetTxt = new JLabel(sujet);
			lbsujetTxt.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbsujetTxt.setSize(300, 20);
			lbsujetTxt.setLocation(120, 150);
			contentPane.add(lbsujetTxt);
			
			JLabel lbResume = new JLabel("Resume : ");
			lbResume.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lbResume.setSize(300, 20);
			lbResume.setLocation(50, 200);
			contentPane.add(lbResume);
			
			
	        
			JTextArea txtResume = new JTextArea(resume, 385, 175);
			txtResume.setSize(385, 175);
			txtResume.setLocation(50, 250);
			txtResume.setEditable(false);
			txtResume.setLineWrap(true);
			txtResume.setWrapStyleWord(true);
			contentPane.add(txtResume);
		}
	    

		/** Renvoie l'instance de la FenetreInfoLivre si elle existe ou en crée une si elle n'existe pas
	      * @param titre titre
	      * @param auteur auteur
	      * @param resume resume
	      * @param sujet sujet
              * @return  instance du singleton FenetreConnexion
              */
	    public static FenetreInfoLivre getInstance(String titre, String Auteur, String Resume, String Sujet) {
	        if( instance == null ) {
	            instance = new FenetreInfoLivre(titre, Auteur, Resume, Sujet);
	        }
	        return instance;
	    }

		/**
                * Supprime l'instance de la FenetreInfoLivre et ferme la fenetre à l'ecran
                 */
	    public static void killInstance() {
	        if (instance != null)
	            instance.setVisible(false);
	        instance = null;
	    }

	    
	}
