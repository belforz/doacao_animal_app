package forms;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JPanel painelPrincipal;
    private JLabel labelLogo;
    private JLabel labelEmail;
    private JTextField textFieldEmail;
    private JLabel labelSenha;
    private JPasswordField passwordFieldSenha;
    private JButton buttonLogin;

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

        // Configurar o label do logo para aceitar imagem
        // Para adicionar uma imagem, use o método setLogoImage()
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);

        // Adicionar action listener ao botão de login
        buttonLogin.addActionListener(e -> realizarLogin());
    }

    /**
     * Método para definir a imagem do logo
     * @param caminhoImagem Caminho para o arquivo de imagem
     */
    public void setLogoImage(String caminhoImagem) {
        try {
            ImageIcon logoIcon = new ImageIcon(caminhoImagem);
            // Redimensionar a imagem se necessário
            Image image = logoIcon.getImage();
            Image newimg = image.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            logoIcon = new ImageIcon(newimg);
            labelLogo.setIcon(logoIcon);
        } catch (Exception e) {
            System.err.println("Erro ao carregar imagem do logo: " + e.getMessage());
            labelLogo.setText("Logo");
        }
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

        // Aqui você pode adicionar a lógica de autenticação
        // Por exemplo, verificar no banco de dados
        System.out.println("Tentativa de login com email: " + email);

        // Exemplo de validação básica (substituir por validação real)
        JOptionPane.showMessageDialog(this,
            "Login em desenvolvimento...",
            "Informação",
            JOptionPane.INFORMATION_MESSAGE);
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

            // Exemplo de como adicionar uma imagem (descomente e ajuste o caminho)
            // loginFrame.setLogoImage("src/main/resources/logo.png");

            loginFrame.setVisible(true);
        });
    }
}

