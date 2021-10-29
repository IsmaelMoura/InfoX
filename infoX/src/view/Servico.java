package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Servico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisar;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Servico dialog = new Servico();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Servico() {
		setModal(true);
		setTitle("Ordem de Servi\u00E7o");
		setResizable(false);
		setBounds(150, 150, 714, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/img/pesquisar.png")));
			lblNewLabel.setBounds(230, 13, 32, 32);
			contentPanel.add(lblNewLabel);
		}
		{
			txtPesquisar = new JTextField();
			txtPesquisar.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					pesquisarCliente();
				}
			});
			txtPesquisar.setBounds(20, 20, 200, 20);
			contentPanel.add(txtPesquisar);
			txtPesquisar.setColumns(10);
		}
		{
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBounds(20, 51, 658, 95);
			contentPanel.add(desktopPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 658, 95);
				desktopPane.add(scrollPane);
				{
					table = new JTable();
					scrollPane.setViewportView(table);
				}
			}
		}
	}// fim do construtor
	DAO dao = new DAO();
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as Cliente, fone as Fone, cep as CEP, endereco as Endereço, numero as Número, complemento as Complemento, bairro as Bairro, cidade as Cidade, uf as UF from clientes where nome like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}// end of the pesquisarCliente()

}
