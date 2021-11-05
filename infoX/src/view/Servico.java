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
import javax.swing.border.TitledBorder;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;

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
		setBounds(150, 150, 902, 514);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(Servico.class.getResource("/img/pesquisar.png")));
			lblNewLabel.setBounds(705, 9, 32, 32);
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
			txtPesquisar.setBounds(532, 15, 163, 20);
			contentPanel.add(txtPesquisar);
			txtPesquisar.setColumns(10);
		}
		{
			JDesktopPane desktopPane = new JDesktopPane();
			desktopPane.setBounds(532, 51, 334, 95);
			contentPanel.add(desktopPane);
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(0, 0, 334, 95);
				desktopPane.add(scrollPane);
				{
					table = new JTable();
					table.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							setarCampos();
						}
					});
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JLabel lblNewLabel_1 = new JLabel("ID");
			lblNewLabel_1.setBounds(775, 18, 32, 14);
			contentPanel.add(lblNewLabel_1);
		}
		
		txtId = new JTextField();
		txtId.setBounds(817, 15, 49, 20);
		contentPanel.add(txtId);
		txtId.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "OS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 25, 440, 124);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(20, 30, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(237, 30, 193, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(237, 11, 39, 14);
		panel.add(lblNewLabel_2);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Or\u00E7amento");
		chckbxNewCheckBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxNewCheckBox.setBounds(20, 82, 103, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxServio = new JCheckBox("Servi\u00E7o");
		chckbxServio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chckbxServio.setBounds(125, 82, 73, 23);
		panel.add(chckbxServio);
		
		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(237, 61, 46, 14);
		panel.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Aguardando aprova\u00E7\u00E3o", "Na bancada", "Aguardando retirada", "Retirado"}));
		comboBox.setBounds(237, 82, 193, 22);
		panel.add(comboBox);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Servico.class.getResource("/img/smartphone.png")));
		lblNewLabel_4.setBorder(null);
		lblNewLabel_4.setBounds(20, 205, 128, 128);
		contentPanel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Dados", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(183, 205, 683, 124);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Equipamento");
		lblNewLabel_5.setBounds(20, 25, 77, 14);
		panel_1.add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.setBounds(107, 22, 556, 20);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Defeito");
		lblNewLabel_6.setBounds(20, 60, 46, 14);
		panel_1.add(lblNewLabel_6);
		
		textField_3 = new JTextField();
		textField_3.setBounds(76, 57, 587, 20);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("T\u00E9cnico");
		lblNewLabel_7.setBounds(20, 95, 46, 14);
		panel_1.add(lblNewLabel_7);
		
		textField_4 = new JTextField();
		textField_4.setBounds(76, 92, 274, 20);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Valor");
		lblNewLabel_8.setBounds(369, 95, 37, 14);
		panel_1.add(lblNewLabel_8);
		
		textField_5 = new JTextField();
		textField_5.setBounds(416, 92, 86, 20);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JButton btnAdicionar = new JButton("");
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Servico.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(20, 384, 70, 70);
		contentPanel.add(btnAdicionar);
		
		JButton btnPesquisar = new JButton("");
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Servico.class.getResource("/img/read.png")));
		btnPesquisar.setBounds(100, 384, 70, 70);
		contentPanel.add(btnPesquisar);
		
		JButton btnEditar = new JButton("");
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Servico.class.getResource("/img/update.png")));
		btnEditar.setBounds(180, 384, 70, 70);
		contentPanel.add(btnEditar);
		
		JButton btnExcluir = new JButton("");
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Servico.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(260, 384, 70, 70);
		contentPanel.add(btnExcluir);
	}// fim do construtor

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Método responsável por pesquisar o cliente no banco dedos
	 */
	private void pesquisarCliente() {
		String read = "select idcli as ID, nome as Cliente, fone as Fone  from clientes where nome like ?";
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
	
	/**
	 * Método e responsável por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] [4]....
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
	}// fim do metodo setarCampos()
}
