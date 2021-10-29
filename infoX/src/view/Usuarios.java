package view;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Atxy2k.CustomTextField.RestrictedTextField;
import model.DAO;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Usuarios extends JDialog {
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Usuarios dialog = new Usuarios();
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
	public Usuarios() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Usuarios.class.getResource("/img/pc.png")));
		setTitle("Cadastrar usu\u00E1rios");
		setModal(true);
		setResizable(false);
		setBounds(150, 150, 604, 357);
		getContentPane().setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Usuarios.class.getResource("/img/pesquisar.png")));
		lblNewLabel_1.setBounds(280, 13, 32, 32);
		getContentPane().add(lblNewLabel_1);

		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBounds(20, 50, 548, 96);
		getContentPane().add(desktopPane);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 548, 96);
		desktopPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setarCampos();
				setarSenha();
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setBounds(20, 170, 26, 14);
		getContentPane().add(lblNewLabel_2);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(56, 167, 50, 20);
		getContentPane().add(txtId);
		txtId.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Usu\u00E1rio");
		lblNewLabel_3.setBounds(130, 170, 46, 14);
		getContentPane().add(lblNewLabel_3);

		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				pesquisarUsuario();
			}
		});
		txtPesquisar.setBounds(20, 20, 250, 20);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(186, 167, 199, 20);
		getContentPane().add(txtUsuario);
		txtUsuario.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Login");
		lblNewLabel_4.setBounds(20, 200, 37, 14);
		getContentPane().add(lblNewLabel_4);

		txtLogin = new JTextField();
		txtLogin.setBounds(66, 197, 110, 20);
		getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("Senha");
		lblNewLabel_5.setBounds(186, 200, 46, 14);
		getContentPane().add(lblNewLabel_5);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setBounds(479, 255, 89, 23);
		getContentPane().add(btnLimpar);

		btnAdicionar = new JButton("");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adicionarUsuario();
			}
		});
		btnAdicionar.setToolTipText("Adicionar");
		btnAdicionar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/create.png")));
		btnAdicionar.setBounds(20, 237, 70, 70);
		getContentPane().add(btnAdicionar);

		btnEditar = new JButton("");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editarUsuario();
			}
		});
		btnEditar.setEnabled(false);
		btnEditar.setToolTipText("Editar");
		btnEditar.setIcon(new ImageIcon(Usuarios.class.getResource("/img/update.png")));
		btnEditar.setBounds(100, 237, 70, 70);
		getContentPane().add(btnEditar);

		btnExcluir = new JButton("");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluirUsuario();
			}
		});
		btnExcluir.setEnabled(false);
		btnExcluir.setIcon(new ImageIcon(Usuarios.class.getResource("/img/delete.png")));
		btnExcluir.setToolTipText("Excluir");
		btnExcluir.setBounds(180, 237, 70, 70);
		getContentPane().add(btnExcluir);

		txtSenha = new JPasswordField();
		txtSenha.setBounds(242, 198, 326, 20);
		getContentPane().add(txtSenha);

		lblNewLabel_6 = new JLabel("Perfil");
		lblNewLabel_6.setBounds(395, 170, 46, 14);
		getContentPane().add(lblNewLabel_6);

		cboPerfil = new JComboBox();
		cboPerfil.setModel(new DefaultComboBoxModel(new String[] { "", "administrador", "operador" }));
		cboPerfil.setBounds(436, 166, 132, 22);
		getContentPane().add(cboPerfil);
		
		RestrictedTextField usuario = new RestrictedTextField(txtUsuario);
		usuario.setLimit(50);
		RestrictedTextField login = new RestrictedTextField(txtLogin);
		login.setLimit(50);
		RestrictedTextField senha = new RestrictedTextField(txtSenha);
		senha.setLimit(250);

	}// end of the constructor

	DAO dao = new DAO();
	private JTextField txtId;
	private JTextField txtPesquisar;
	private JTextField txtUsuario;
	private JTextField txtLogin;
	private JButton btnAdicionar;
	private JButton btnEditar;
	private JButton btnExcluir;
	private JPasswordField txtSenha;
	private JLabel lblNewLabel_6;
	private JComboBox cboPerfil;

	private void pesquisarUsuario() {
		String read = "select id as ID,usuario as Usuário,login as Login,perfil as Perfil from usuarios where usuario like ?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, txtPesquisar.getText() + "%");
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
		} catch (Exception e) {
			System.out.println(e);
		}
	}// end of the pesquisarUsuario()

	/**
	 * metodo e responsavel por setar os campos da tabela no formulario
	 */
	private void setarCampos() {
		// a linha abaixo obtem o conteudo da linha da tabela
		// int (indice = colunas) [0] [1] [2] [3] [4]....
		int setar = table.getSelectedRow();
		// setar os campos
		txtId.setText(table.getModel().getValueAt(setar, 0).toString());
		txtUsuario.setText(table.getModel().getValueAt(setar, 1).toString());
		txtLogin.setText(table.getModel().getValueAt(setar, 2).toString());
		cboPerfil.setSelectedItem(table.getModel().getValueAt(setar, 3).toString());
		// gerenciar os botoes
		btnAdicionar.setEnabled(false);
		btnEditar.setEnabled(true);
		btnExcluir.setEnabled(true);
	}// fim do metodo setarCampos()

	/**
	 * metodo especifico para setar a senha
	 */
	private void setarSenha() {
		String read2 = "select senha from usuarios where id=?";
		try {
			Connection con = dao.conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, txtId.getText());
			// a linha abaixo executa a instrucao sql e armazena o resultado no objeto rs
			// (ResultSet)
			ResultSet rs = pst.executeQuery();
			// a linha abaixo verifica se existe uma senha para o id
			if (rs.next()) {
				txtSenha.setText(rs.getString(1));
			}
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}// fim do metodo setarSenha()

	/**
	 * método responsável por adicionar um usuario no banco
	 */
	private void adicionarUsuario() {
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuário", "Atenção !", JOptionPane.WARNING_MESSAGE);
			// posicionar o cursor no campo após fechar a mensagem
			txtUsuario.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Atenção !", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Atenção !", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			// inserir o usuario no banco
			String create = "insert into usuarios (usuario,login,senha,perfil) values (?,?,md5(?),?)";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (inserção do usuario no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário incluido com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
				// o catch abaixo se refere ao valor duplicado de usuario(UNIQUE)
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Usuario já cadastrado.\nCadastre outro usuário", "Atenção !",
						JOptionPane.WARNING_MESSAGE);
				txtUsuario.setText(null);
				txtUsuario.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método adicionarUsuario()

	/**
	 * metodo responsavel pela edicao dos dados do cliente
	 */
	private void editarUsuario() {
		// validação de campos obrigatórios
		if (txtUsuario.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Usuário", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtUsuario.requestFocus();
		} else if (cboPerfil.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Perfil", "Aviso", JOptionPane.WARNING_MESSAGE);
			cboPerfil.requestFocus();
		} else if (txtLogin.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Login", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtLogin.requestFocus();
		} else if (txtSenha.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Preencha o campo Senha", "Aviso", JOptionPane.WARNING_MESSAGE);
			txtSenha.requestFocus();
		} else {
			// editar o usuario do banco
			String update = "update usuarios set usuario=?,login=?,senha=md5(?),perfil=? where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, txtUsuario.getText());
				pst.setString(2, txtLogin.getText());
				pst.setString(3, txtSenha.getText());
				pst.setString(4, cboPerfil.getSelectedItem().toString());
				pst.setString(5, txtId.getText());

				// criando uma variavel que irá executar a query e receber o valor 1 em caso
				// positivo (edicao do usuario no banco)
				int confirma = pst.executeUpdate();
				if (confirma == 1) {
					JOptionPane.showMessageDialog(null, "Usuário editado com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
					con.close();
					limpar();
				}
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Edição não realizada.\nLogin já existente", "Aviso",
						JOptionPane.WARNING_MESSAGE);
				txtLogin.setText(null);
				txtLogin.requestFocus();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

	}// fim do metodo editarUsuario();

	private void excluirUsuario() {
		// confimação de exclusão
		int confirma = JOptionPane.showConfirmDialog(null, "Confirma a exclusão deste usuário?", "Atenção!",
				JOptionPane.YES_NO_OPTION);
		if (confirma == JOptionPane.YES_OPTION) {
			String delete = "delete from usuarios where id=?";
			try {
				Connection con = dao.conectar();
				PreparedStatement pst = con.prepareStatement(delete);
				pst.setString(1, txtId.getText());
				int excluir = pst.executeUpdate();
				if (excluir == 1) {
					limpar();
					JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
				con.close();
			} catch (java.sql.SQLIntegrityConstraintViolationException ex) {
				JOptionPane.showMessageDialog(null, "Exclusão não realizada.\nUsuário possui OS em aberto.", "Aviso",
						JOptionPane.WARNING_MESSAGE);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}// fim do método excluirUsuario();

	/**
	 * metodo responsavel por limpar os campos e gerenciar os botoes
	 */
	private void limpar() {
		// limpar campos
		txtPesquisar.setText(null);
		txtId.setText(null);
		txtUsuario.setText(null);
		txtLogin.setText(null);
		txtSenha.setText(null);
		cboPerfil.setSelectedItem(null);
		// limpar tabela
		((DefaultTableModel) table.getModel()).setRowCount(0);
		// gerenciar botoes
		btnAdicionar.setEnabled(true);
		btnEditar.setEnabled(false);
		btnExcluir.setEnabled(false);
	}// fim do metodo limpar()
}
