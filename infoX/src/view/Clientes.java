package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;

import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "serial", "unused" })
public class Clientes extends JDialog {
	private JTextField txtPesquisar;
	private JTextField txtIdCli;
	private JTextField txtNomeCli;
	private JTextField txtFoneCli;
	private JTextField txtCep;
	private JTextField txtEndereco;
	private JTextField txtNumero;
	private JTextField txtComplemento;
	private JTextField txtCidade;
	private JTextField txtBairro;
	@SuppressWarnings("rawtypes")
	private JComboBox cboUf;
	private JLabel lblStatus;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clientes dialog = new Clientes();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Clientes() {
		setTitle("Clientes");
		setModal(true);
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Clientes.class.getResource("/img/pc.png")));
		setBounds(150, 150, 781, 462);
		getContentPane().setLayout(null);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarCliente();
			}
		});
		txtPesquisar.setBounds(20, 30, 200, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/img/pesquisar.png")));
		lblNewLabel.setBounds(227, 23, 32, 32);
		getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("* Campos obrigat\u00F3rios");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(571, 30, 129, 14);
		getContentPane().add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(20, 61, 725, 130);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 725, 130);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(20, 210, 24, 14);
		getContentPane().add(lblNewLabel_2);

		txtIdCli = new JTextField();
		txtIdCli.setEditable(false);
		txtIdCli.setBounds(40, 207, 50, 20);
		getContentPane().add(txtIdCli);
		txtIdCli.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("* Nome");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_3.setBounds(103, 210, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtNomeCli = new JTextField();
		txtNomeCli.setBounds(159, 207, 269, 20);
		getContentPane().add(txtNomeCli);
		txtNomeCli.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("* Fone");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_4.setBounds(448, 210, 46, 14);
		getContentPane().add(lblNewLabel_4);

		txtFoneCli = new JTextField();
		txtFoneCli.setBounds(504, 207, 241, 20);
		getContentPane().add(txtFoneCli);
		txtFoneCli.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("CEP");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_5.setBounds(20, 245, 24, 14);
		getContentPane().add(lblNewLabel_5);

		txtCep = new JTextField();
		txtCep.setBounds(50, 242, 90, 20);
		getContentPane().add(txtCep);
		txtCep.setColumns(10);

		JButton btnCep = new JButton("Buscar");
		btnCep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarCep();
			}
		});
		btnCep.setBounds(170, 241, 89, 23);
		getContentPane().add(btnCep);

		JLabel lblNewLabel_6 = new JLabel("* Endere\u00E7o");
		lblNewLabel_6.setBounds(20, 283, 70, 14);
		getContentPane().add(lblNewLabel_6);

		txtEndereco = new JTextField();
		txtEndereco.setBounds(96, 280, 291, 20);
		getContentPane().add(txtEndereco);
		txtEndereco.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("* N\u00FAmero");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_7.setBounds(397, 283, 53, 14);
		getContentPane().add(lblNewLabel_7);

		txtNumero = new JTextField();
		txtNumero.setBounds(460, 280, 62, 20);
		getContentPane().add(txtNumero);
		txtNumero.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("Complemento");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_8.setBounds(532, 283, 87, 14);
		getContentPane().add(lblNewLabel_8);

		txtComplemento = new JTextField();
		txtComplemento.setBounds(629, 280, 116, 20);
		getContentPane().add(txtComplemento);
		txtComplemento.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("* Bairro");
		lblNewLabel_9.setBounds(20, 314, 56, 14);
		getContentPane().add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("* Cidade");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_10.setBounds(317, 314, 63, 14);
		getContentPane().add(lblNewLabel_10);

		txtCidade = new JTextField();
		txtCidade.setColumns(10);
		txtCidade.setBounds(390, 311, 212, 20);
		getContentPane().add(txtCidade);

		JLabel lblNewLabel_11 = new JLabel("* UF");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_11.setBounds(661, 314, 24, 14);
		getContentPane().add(lblNewLabel_11);

		cboUf = new JComboBox();
		cboUf.setEditable(true);
		cboUf.setModel(new DefaultComboBoxModel(
				new String[] { "", "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA",
						"PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
		cboUf.setBounds(695, 310, 50, 22);
		getContentPane().add(cboUf);

		txtBairro = new JTextField();
		txtBairro.setColumns(10);
		txtBairro.setBounds(86, 311, 221, 20);
		getContentPane().add(txtBairro);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarCliente();
			}
		});
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Clientes.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(20, 342, 70, 70);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarCliente();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/img/update.png")));
		btnEditar.setToolTipText("Editar");
		btnEditar.setBounds(100, 342, 70, 70);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirCliente();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Clientes.class.getResource("/img/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(180, 342, 70, 70);
		getContentPane().add(btnExcluir);

		lblStatus = new JLabel("");
		lblStatus.setBounds(287, 237, 32, 32);
		getContentPane().add(lblStatus);

		// uso da biblioteca Atxy2k para validações
		RestrictedTextField nome = new RestrictedTextField(this.txtNomeCli);
		nome.setLimit(50);
		RestrictedTextField cep = new RestrictedTextField(this.txtCep);
		cep.setLimit(8);
		cep.setOnlyNums(true);
		RestrictedTextField endereco = new RestrictedTextField(this.txtEndereco);
		endereco.setLimit(50);
		RestrictedTextField numero = new RestrictedTextField(this.txtNumero);
		numero.setLimit(12);
		numero.setOnlyNums(true);
		RestrictedTextField complemento = new RestrictedTextField(this.txtComplemento);
		complemento.setLimit(30);
		RestrictedTextField bairro = new RestrictedTextField(this.txtBairro);
		bairro.setLimit(50);
		RestrictedTextField cidade = new RestrictedTextField(this.txtCidade);
		cidade.setLimit(50);
		RestrictedTextField fone = new RestrictedTextField(this.txtFoneCli);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(656, 366, 89, 23);
		getContentPane().add(btnLimpar);
		fone.setLimit(15);

	}// end of the constructor

	/**
	 * buscarCep
	 */
	private void buscarCep() {
		String logradouro = "";
		String tipoLogradouro = "";
		String resultado = null;
		String cep = txtCep.getText();
		try {
			URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
			SAXReader xml = new SAXReader();
			Document documento = xml.read(url);
			Element root = documento.getRootElement();
			for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
				Element element = it.next();
				if (element.getQualifiedName().equals("cidade")) {
					txtCidade.setText(element.getText());
				}
				if (element.getQualifiedName().equals("bairro")) {
					txtBairro.setText(element.getText());
				}
				if (element.getQualifiedName().equals("uf")) {
					cboUf.setSelectedItem(element.getText());
				}
				if (element.getQualifiedName().equals("tipo_logradouro")) {
					tipoLogradouro = element.getText();
				}
				if (element.getQualifiedName().equals("logradouro")) {
					logradouro = element.getText();
				}
				if (element.getQualifiedName().equals("resultado")) {
					resultado = element.getText();
					if (resultado.equals("1")) {
						lblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/checked.png")));
					} else {
						JOptionPane.showMessageDialog(null, "CEP não encontrado");
					}
				}
			}

			txtEndereco.setText(tipoLogradouro + " " + logradouro);
		} catch (Exception e) {
			System.out.println(e);
		}
	}// end of the buscarCep()

	DAO dao = new DAO();
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;

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

	/**
	 * método responsável por adicionar um cliente no banco
	 */
	private void adicionarCliente() {
		// validação de campos obrigatórios
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNomeCli.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo fone", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtFoneCli.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo endereço", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo número", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo bairro", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Atenção !", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {

			// inserir o cliente no banco
			String create = "insert into clientes (nome,cep,endereco,numero,complemento,bairro,cidade,uf,fone) values (?,?,?,?,?,?,?,?,?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtFoneCli.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente incluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método adicionarCliente()

	/**
	 * metodo e responsavel por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] [4]....
		int setar = table.getSelectedRow();
		// setar os campos
		txtIdCli.setText(table.getModel().getValueAt(setar, 0).toString());
		txtNomeCli.setText(table.getModel().getValueAt(setar, 1).toString());
		txtFoneCli.setText(table.getModel().getValueAt(setar, 2).toString());
		txtCep.setText(table.getModel().getValueAt(setar, 3).toString());
		txtEndereco.setText(table.getModel().getValueAt(setar, 4).toString());
		txtNumero.setText(table.getModel().getValueAt(setar, 5).toString());
		txtComplemento.setText(table.getModel().getValueAt(setar, 6).toString());
		txtBairro.setText(table.getModel().getValueAt(setar, 7).toString());
		txtCidade.setText(table.getModel().getValueAt(setar, 8).toString());
		cboUf.setSelectedItem(table.getModel().getValueAt(setar, 9).toString());
		// gerenciar os botoes
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}// fim do metodo setarCampos()

	/**
	 * metodo responsavel pela edicao dos dados do cliente
	 */
	private void editarCliente() {
		// validação de campos obrigatórios
		if (txtNomeCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo nome", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtNomeCli.requestFocus();
		} else if (txtFoneCli.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Fone", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtFoneCli.requestFocus();
		} else if (txtEndereco.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Endereço", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtEndereco.requestFocus();
		} else if (txtNumero.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Número", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtNumero.requestFocus();
		} else if (txtBairro.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Bairro", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtBairro.requestFocus();
		} else if (txtCidade.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Cidade", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtCidade.requestFocus();
		} else if (cboUf.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo UF", "Aviso", JOptionPane.WARNING_MESSAGE);
			cboUf.requestFocus();
		} else {
			// editar o cliente do banco
			String update = "update clientes set nome=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,fone=? where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtNomeCli.getText());
				pst.setString(2, txtCep.getText());
				pst.setString(3, txtEndereco.getText());
				pst.setString(4, txtNumero.getText());
				pst.setString(5, txtComplemento.getText());
				pst.setString(6, txtBairro.getText());
				pst.setString(7, txtCidade.getText());
				pst.setString(8, cboUf.getSelectedItem().toString());
				pst.setString(9, txtFoneCli.getText());
				pst.setString(10, txtIdCli.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edicao do cliente no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Cliente editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// fim do metodo editarCliente()
	
	private void excluirCliente() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste cliente?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from clientes where idcli=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtIdCli.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nCliente possui serviço em aberto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método excluirCliente()

	/**
	 * Limpar os campos
	 */
	private void limpar() {
		// limpar campos
		txtPesquisar.setText(null);
		txtIdCli.setText(null);
		txtNomeCli.setText(null);
		txtCep.setText(null);
		txtEndereco.setText(null);
		txtNumero.setText(null);
		txtComplemento.setText(null);
		txtBairro.setText(null);
		txtCidade.setText(null);
		cboUf.setSelectedItem(null);
		txtFoneCli.setText(null);
		// limpar tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}
}
