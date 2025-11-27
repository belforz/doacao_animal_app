package forms;

import javax.swing.*;
import java.awt.*;
import DAO.LoginDAO;
import schemas.Adotante;
import schemas.Protetor;
import exceptions.CustomException;

public class Login extends JFrame {
    private JPanel painelPrincipal;
    private JLabel labelLogo;
    private JLabel labelEmail;
    private JTextField textFieldEmail;
    private JLabel labelSenha;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;
    private JComboBox comboBoxTipo;

    public Login() {
        // Configuração da janela
        setTitle("Sistema de Adoção Animal - Login");
        painelPrincipal = new JPanel(new GridBagLayout());
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centraliza na tela

        // Inicializar componentes
        labelLogo = new JLabel("");
        try {
            labelLogo.setIcon(new ImageIcon(getClass().getResource("/logo (1).jpg")));
        } catch (Exception e) {
            // Icon not found
        }
        labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelEmail.setForeground(Color.BLACK);
        textFieldEmail = new JTextField();
        textFieldEmail.setBackground(Color.WHITE);
        textFieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelSenha = new JLabel("Senha:");
        labelSenha.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelSenha.setForeground(Color.BLACK);
        passwordFieldSenha = new JPasswordField();
        passwordFieldSenha.setBackground(Color.WHITE);
        passwordFieldSenha.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        comboBoxTipo = new JComboBox();
        buttonLogin = new JButton("Entrar");
        buttonLogin.setBackground(new Color(0, 123, 255));
        buttonLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonLogin.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        // Adicionar labelLogo
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.insets = new Insets(20, 40, 10, 40);
        painelPrincipal.add(labelLogo, gbc);

        // Adicionar labelEmail
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelEmail, gbc);

        // Adicionar textFieldEmail
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldEmail, gbc);

        // Adicionar labelSenha
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelSenha, gbc);

        // Adicionar passwordFieldSenha
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(passwordFieldSenha, gbc);

        // Adicionar labelTipo
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        JLabel labelTipo = new JLabel("Tipo");
        labelTipo.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelTipo.setForeground(Color.BLACK);
        painelPrincipal.add(labelTipo, gbc);

        // Adicionar comboBoxTipo
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(comboBoxTipo, gbc);

        // Adicionar buttonLogin
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 40);
        painelPrincipal.add(buttonLogin, gbc);

        // Configurar cor de fundo do painel
        painelPrincipal.setBackground(new Color(160, 160, 160)); // Cinza

        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);

        // Adiconar opções ao combo box
        comboBoxTipo.addItem("Adotante");
        comboBoxTipo.addItem("Protetor");
        comboBoxTipo.addItem("Admin");

        // Adicionar action listener ao botão de login
        buttonLogin.addActionListener(e -> realizarLogin());
    }


    /**
     * Método chamado quando o botão de login é clicado
     */
    private void realizarLogin() {
        String email = textFieldEmail.getText();
        String senha = new String(passwordFieldSenha.getPassword());

        if (email.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Por favor, preencha todos os campos!",
                "Campos vazios",
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        String tipoSelecionado = (String) comboBoxTipo.getSelectedItem();

        if ("Admin".equals(tipoSelecionado)) {
            if (email.equals("adm@email.com")) {
                JOptionPane.showMessageDialog(this,
                    "Login realizado com sucesso como ADMIN!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                Admin admin = new Admin();
                admin.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this,
                    "Email ou senha incorretos para Admin!",
                    "Erro de Login",
                    JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        try {
            LoginDAO loginDAO = new LoginDAO();

            if ("Adotante".equals(tipoSelecionado)) {
                Adotante adotante = loginDAO.loginAdotante(email, senha);
                if (adotante != null) {
                    JOptionPane.showMessageDialog(this,
                        "Login realizado com sucesso como Adotante!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    Welcome welcome = new Welcome("Adotante", adotante);
                    welcome.setVisible(true);
                    return;
                }
            } else if ("Protetor".equals(tipoSelecionado)) {
                Protetor protetor = loginDAO.loginProtetor(email, senha);
                if (protetor != null) {
                    JOptionPane.showMessageDialog(this,
                        "Login realizado com sucesso como Protetor!",
                        "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    Welcome welcome = new Welcome("Protetor", protetor);
                    welcome.setVisible(true);
                    return;
                }
            }

            JOptionPane.showMessageDialog(this,
                "Email ou senha incorretos para o tipo selecionado!",
                "Erro de Login",
                JOptionPane.ERROR_MESSAGE);

        } catch (CustomException e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao realizar login: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Retorna o email digitado
     */
    public String getEmail() {
        return textFieldEmail.getText();
    }

    /**
     * Retorna a senha digitada
     */
    public String getSenha() {
        return new String(passwordFieldSenha.getPassword());
    }

    /**
     * Limpa os campos de email e senha
     */
    public void limparCampos() {
        textFieldEmail.setText("");
        passwordFieldSenha.setText("");
    }

    /**
     * Método main para testar a tela de login
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login loginFrame = new Login();

            loginFrame.setVisible(true);
        });
    }
}
