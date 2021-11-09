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
import javax.swing.JOptionPane;
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
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Servico extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtPesquisar;
	private JTable table;
	// variável de apoio ao uso do checkbox
	private String tipo;

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

		txtOs = new JTextField();
		txtOs.setEditable(false);
		txtOs.setBounds(20, 30, 86, 20);
		panel.add(txtOs);
		txtOs.setColumns(10);

		txtDataOs = new JTextField();
		txtDataOs.setEditable(false);
		txtDataOs.setBounds(237, 30, 193, 20);
		panel.add(txtDataOs);
		txtDataOs.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Data");
		lblNewLabel_2.setBounds(237, 11, 39, 14);
		panel.add(lblNewLabel_2);

		chkOrcamento = new JCheckBox("Or\u00E7amento");
		chkOrcamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Orçamento";
			}
		});
		buttonGroup.add(chkOrcamento);
		chkOrcamento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chkOrcamento.setBounds(20, 82, 103, 23);
		panel.add(chkOrcamento);

		chkServico = new JCheckBox("Servi\u00E7o");
		chkServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tipo = "Serviço";
			}
		});
		buttonGroup.add(chkServico);
		chkServico.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		chkServico.setBounds(125, 82, 73, 23);
		panel.add(chkServico);

		JLabel lblNewLabel_3 = new JLabel("Status");
		lblNewLabel_3.setBounds(237, 61, 46, 14);
		panel.add(lblNewLabel_3);

		cboStatus = new JComboBox();
		cboStatus.setModel(new DefaultComboBoxModel(new String[] { "", "Aguardando aprova\u00E7\u00E3o", "Na bancada",
				"Aguardando retirada", "Retirado" }));
		cboStatus.setBounds(237, 82, 193, 22);
		panel.add(cboStatus);

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

		txtEquipamento = new JTextField();
		txtEquipamento.setBounds(107, 22, 556, 20);
		panel_1.add(txtEquipamento);
		txtEquipamento.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("Defeito");
		lblNewLabel_6.setBounds(20, 60, 46, 14);
		panel_1.add(lblNewLabel_6);

		txtDefeito = new JTextField();
		txtDefeito.setBounds(76, 57, 587, 20);
		panel_1.add(txtDefeito);
		txtDefeito.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("T\u00E9cnico");
		lblNewLabel_7.setBounds(20, 95, 46, 14);
		panel_1.add(lblNewLabel_7);

		txtTecnico = new JTextField();
		txtTecnico.setBounds(76, 92, 274, 20);
		panel_1.add(txtTecnico);
		txtTecnico.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Valor");
		lblNewLabel_8.setBounds(369, 95, 37, 14);
		panel_1.add(lblNewLabel_8);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				emitirOs();
			}
		});
		btnAdicionar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdicionar.setIcon(new ImageIcon(Servico.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(20, 384, 70, 70);
		contentPanel.add(btnAdicionar);

		btnPesquisar = new JButton("");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pesquisarOs();
			}
		});
		btnPesquisar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPesquisar.setIcon(new ImageIcon(Servico.class.getResource("/img/read.png")));
		btnPesquisar.setBounds(100, 384, 70, 70);
		contentPanel.add(btnPesquisar);

		btnEditar = new JButton("");
		btnEditar.setEnabled(false);
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarOs();
			}
		});
		btnEditar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnEditar.setIcon(new ImageIcon(Servico.class.getResource("/img/update.png")));
		btnEditar.setBounds(180, 384, 70, 70);
		contentPanel.add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirOs();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnExcluir.setIcon(new ImageIcon(Servico.class.getResource("/img/delete.png")));
		btnExcluir.setBounds(260, 384, 70, 70);
		contentPanel.add(btnExcluir);

		btnImprimir = new JButton("");
		btnImprimir.setToolTipText("Imprimir");
		btnImprimir.setIcon(new ImageIcon(Servico.class.getResource("/img/print.png")));
		btnImprimir.setBounds(340, 384, 70, 70);
		contentPanel.add(btnImprimir);

		txtTotal = new JTextField();
		txtTotal.setBounds(416, 92, 86, 20);
		panel_1.add(txtTotal);
		txtTotal.setColumns(10);

		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(777, 431, 89, 23);
		contentPanel.add(btnLimpar);

		RestrictedTextField equipamento = new RestrictedTextField(txtEquipamento);
		equipamento.setLimit(200);
		RestrictedTextField defeito = new RestrictedTextField(txtDefeito);
		defeito.setLimit(200);
		RestrictedTextField tecnico = new RestrictedTextField(txtTecnico);
		tecnico.setLimit(50);

	}// fim do construtor

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtOs;
	private JTextField txtDataOs;
	private JTextField txtEquipamento;
	private JTextField txtDefeito;
	private JTextField txtTecnico;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JCheckBox chkOrcamento;
	private JCheckBox chkServico;
	private JComboBox cboStatus;
	private JButton btnAdicionar;
	private JButton btnPesquisar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JButton btnImprimir;
	private JButton btnLimpar;
	private JTextField txtTotal;

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

	/**
	 * Método responsável pela pesquisa da OS
	 */
	private void pesquisarOs() {
		// técnica usada para capturar o número da OS
		String numOs = JOptionPane.showInputDialog("Número da OS");
		String read = "select * from tbos where os=" + numOs;
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				txtId.setText(rs.getString(9));
				txtOs.setText(rs.getString(1));
				txtDataOs.setText(rs.getString(2));
				if (rs.getString(3).equals("Orçamento")) {
					chkOrcamento.setSelected(true);
				} else {
					chkServico.setSelected(true);
				}
				cboStatus.setSelectedItem(rs.getString(4));
				txtEquipamento.setText(rs.getString(5));
				txtDefeito.setText(rs.getString(6));
				txtTecnico.setText(rs.getString(7));
				txtTotal.setText(rs.getString(8));
				btnAdicionar.setEnabled(false);
				btnEditar.setEnabled(true);
				btnExcluir.setEnabled(true);
			} else {
				JOptionPane.showMessageDialog(null, "Número de OS não existe", "Atenção !",
						JOptionPane.WARNING_MESSAGE);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do método pesquisarOs()

	/*
	 * Método responsável por emitir uma nova OS(inserir OS)
	 */
	private void emitirOs() {
		// validação
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtId.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStatus.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			String create = "insert into tbos (tipo,statusos,equipamento,defeito,tecnico,valor,idcli) values (?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, tipo);
				pst.setString(2, cboStatus.getSelectedItem().toString());
				pst.setString(3, txtEquipamento.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtTecnico.getText());
				pst.setString(6, txtTotal.getText());
				pst.setString(7, txtId.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS emitida com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método emitirOs()

	/**
	 * Método responsável por editar os dados da OS no banco de dados
	 */
	private void editarOs() {
		// validação
		if (txtId.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha ID do cliente", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtId.requestFocus();
		} else if (tipo == null) {
			JOptionPane.showMessageDialog(null, "Selecione o tipo de OS", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else if (cboStatus.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Selecione qual o status da OS", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			cboStatus.requestFocus();
		} else if (txtEquipamento.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Equipamento", "Atenção !",
					JOptionPane.WARNING_MESSAGE);
			txtEquipamento.requestFocus();
		} else if (txtDefeito.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Defeito", "Atenção !", JOptionPane.WARNING_MESSAGE);
			chkOrcamento.requestFocus();
		} else {
			// editar os dados da OS no banco de dados
			String update = "update tbos set tipo=?,statusos=?,equipamento=?,defeito=?,tecnico=?,valor=? where os=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, tipo);
				pst.setString(2, cboStatus.getSelectedItem().toString());
				pst.setString(3, txtEquipamento.getText());
				pst.setString(4, txtDefeito.getText());
				pst.setString(5, txtTecnico.getText());
				pst.setString(6, txtTotal.getText());
				pst.setString(7, txtOs.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edição da OS no banco de dados)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "OS editada com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// fim do metodo editarOs()

	/**
	 * Método responsável por excluir a OS do banco de dados
	 */
	private void excluirOs() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão desta OS?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from tbos where os=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtOs.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "OS excluída com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método excluirOs()

	/**
	 * Método responsável por zerar todos os campos
	 */
	private void limpar() {
		// limpar campos
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtOs.setText(null);
		txtDataOs.setText(null);
		// tirar seleção do checkbox
		buttonGroup.clearSelection();
		cboStatus.setSelectedItem(null);
		txtEquipamento.setText(null);
		txtDefeito.setText(null);
		txtTecnico.setText(null);
		txtTotal.setText(null);
		// limpar tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
		tipo = null;
	}// fim do método limpar()
}
