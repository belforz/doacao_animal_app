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
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null); // Centraliza na tela

        // Configurar cor de fundo do painel
        painelPrincipal.setBackground(new Color(160, 160, 160)); // Cinza

        // Configurar cores dos labels
        labelEmail.setForeground(Color.BLACK);
        labelSenha.setForeground(Color.BLACK);

        // Configurar cores dos text fields
        textFieldEmail.setBackground(Color.WHITE);
        passwordFieldSenha.setBackground(Color.WHITE);

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
            if (email.equals("admin@email.com") && senha.equals("admin")) {
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
