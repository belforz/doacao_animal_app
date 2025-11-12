package forms;

import DAO.LoginDAO;
import exceptions.CustomException;
import schemas.Adotante;
import schemas.Protetor;

import javax.swing.*;
import java.awt.*;

public class Welcome extends JFrame {
    private JPanel painelPrincipal;
    private JLabel labelBoasVindas;
    private JLabel labelNome;
    private JTextField textFieldNome;
    private JLabel labelEmail;
    private JTextField textFieldEmail;
    private JLabel labelDocumento;
    private JTextField textFieldDocumento;
    private JLabel labelTelefone;
    private JTextField textFieldTelefone;
    private JLabel labelEndereco;
    private JTextField textFieldEndereco;
    private JLabel labelEspecifico;
    private JTextField textFieldEspecifico;
    private JButton buttonVerAnimais;
    private JButton buttonVerProcessos;
    private JButton buttonLogout;

    private String tipoUsuario; // "Protetor" ou "Adotante"
    private Object usuario; // Instância de Protetor ou Adotante

    public Welcome(String tipoUsuario, Object usuario) {
        this.tipoUsuario = tipoUsuario;
        this.usuario = usuario;

        // Garantir que os componentes existam — inicializa programaticamente se o .form não for carregado
        if (painelPrincipal == null) {
            initComponents();
        }

        // Configuração da janela
        setTitle("Sistema de Adoção Animal - Bem-vindo");
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza na tela

        // Configurar cor de fundo do painel
        painelPrincipal.setBackground(new Color(160, 160, 160)); // Cinza

        // Configurar cores dos labels
        labelBoasVindas.setForeground(Color.BLACK);
        labelNome.setForeground(Color.BLACK);
        labelEmail.setForeground(Color.BLACK);
        labelDocumento.setForeground(Color.BLACK);
        labelTelefone.setForeground(Color.BLACK);
        labelEndereco.setForeground(Color.BLACK);
        labelEspecifico.setForeground(Color.BLACK);

        // Configurar cores dos text fields
        textFieldNome.setBackground(Color.WHITE);
        textFieldEmail.setBackground(Color.WHITE);
        textFieldDocumento.setBackground(Color.WHITE);
        textFieldTelefone.setBackground(Color.WHITE);
        textFieldEndereco.setBackground(Color.WHITE);
        textFieldEspecifico.setBackground(Color.WHITE);


        // Configurar label específico baseado no tipo de usuário
        if ("Protetor".equals(tipoUsuario)) {
            labelEspecifico.setText("Tipo:");
            labelBoasVindas.setText("Bem-vindo, Protetor!");
        } else if ("Adotante".equals(tipoUsuario)) {
            labelEspecifico.setText("Preferência de Adoção:");
            labelBoasVindas.setText("Bem-vindo, Adotante!");
        }

        // Preencher os campos com dados do usuário
        preencherDadosUsuario();

        // Adicionar action listeners aos botões
        buttonVerAnimais.addActionListener(e -> verAnimais());
        buttonVerProcessos.addActionListener(e -> verProcessos());
        buttonLogout.addActionListener(e -> logout());

        // Debug: confirmar construção
        System.out.println("[DEBUG] Welcome constructed: tipoUsuario=" + tipoUsuario + ", usuario=" + (usuario==null?"null":usuario.getClass().getSimpleName()));
    }

    /**
     * Preenche os campos com os dados do usuário logado
     */
    private void preencherDadosUsuario() {
        try {
            if (usuario == null) return;
            Class<?> clazz = usuario.getClass();
            try { textFieldNome.setText((String) clazz.getMethod("getNome").invoke(usuario)); } catch (Exception ignored) {}
            try { textFieldEmail.setText((String) clazz.getMethod("getEmail").invoke(usuario)); } catch (Exception ignored) {}
            try { textFieldDocumento.setText((String) clazz.getMethod("getDocumento").invoke(usuario)); } catch (Exception ignored) {}
            try { textFieldTelefone.setText((String) clazz.getMethod("getTelefone").invoke(usuario)); } catch (Exception ignored) {}
            try { textFieldEndereco.setText((String) clazz.getMethod("getEndereco").invoke(usuario)); } catch (Exception ignored) {}

            // Campo específico
            if ("Protetor".equals(tipoUsuario)) {
                try { textFieldEspecifico.setText((String) clazz.getMethod("getTipo").invoke(usuario)); } catch (Exception ignored) {}
            } else if ("Adotante".equals(tipoUsuario)) {
                try { textFieldEspecifico.setText((String) clazz.getMethod("getPreferenciaAdocao").invoke(usuario)); } catch (Exception ignored) {}
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar dados do usuário: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Método chamado ao clicar em "Ver Animais"
     */
    private void verAnimais() {
        JOptionPane.showMessageDialog(this,
            "Funcionalidade 'Ver Animais' em desenvolvimento...",
            "Informação",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método chamado ao clicar em "Ver Processos"
     */
    private void verProcessos() {
        JOptionPane.showMessageDialog(this,
            "Funcionalidade 'Ver Processos' em desenvolvimento...",
            "Informação",
            JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Método chamado ao clicar em "Sair"
     */
    private void logout() {
        int resposta = JOptionPane.showConfirmDialog(this,
            "Deseja realmente sair?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            // Fechar esta janela e abrir a tela de login
            this.dispose();
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        }
    }

    /**
     * Método para obter o tipo de usuário
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Método para obter o objeto usuário
     */
    public Object getUsuario() {
        return usuario;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Prompt para email e senha
            String email = JOptionPane.showInputDialog(null, "Digite o email:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (email == null || email.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email não informado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String senha = JOptionPane.showInputDialog(null, "Digite a senha:", "Login", JOptionPane.QUESTION_MESSAGE);
            if (senha == null || senha.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Senha não informada.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                LoginDAO loginDAO = new LoginDAO();

                // Tentar login como Adotante
                Adotante adotante = loginDAO.loginAdotante(email, senha);
                if (adotante != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado como Adotante!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    Welcome welcomeFrame = new Welcome("Adotante", adotante);
                    welcomeFrame.setVisible(true);
                    return;
                }

                // Tentar login como Protetor
                Protetor protetor = loginDAO.loginProtetor(email, senha);
                if (protetor != null) {
                    JOptionPane.showMessageDialog(null, "Login realizado como Protetor!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    Welcome welcomeFrame = new Welcome("Protetor", protetor);
                    welcomeFrame.setVisible(true);
                    return;
                }

                // Se nenhum login funcionou
                JOptionPane.showMessageDialog(null, "Email ou senha incorretos!", "Erro de Login", JOptionPane.ERROR_MESSAGE);

            } catch (CustomException e) {
                JOptionPane.showMessageDialog(null, "Erro ao realizar login: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Adicionando método initComponents para inicializar a UI quando o .form não for carregado
    private void initComponents() {
        painelPrincipal = new JPanel(new GridBagLayout());

        labelBoasVindas = new JLabel("Bem-vindo!");
        labelBoasVindas.setFont(new Font("Segoe UI", Font.BOLD, 18));

        labelNome = new JLabel("Nome:");
        textFieldNome = new JTextField();
        textFieldNome.setEditable(false);
        textFieldNome.setPreferredSize(new Dimension(300, 30));

        labelEmail = new JLabel("Email:");
        textFieldEmail = new JTextField();
        textFieldEmail.setEditable(false);
        textFieldEmail.setPreferredSize(new Dimension(300, 30));

        labelDocumento = new JLabel("Documento:");
        textFieldDocumento = new JTextField();
        textFieldDocumento.setEditable(false);
        textFieldDocumento.setPreferredSize(new Dimension(300, 30));

        labelTelefone = new JLabel("Telefone:");
        textFieldTelefone = new JTextField();
        textFieldTelefone.setEditable(false);
        textFieldTelefone.setPreferredSize(new Dimension(300, 30));

        labelEndereco = new JLabel("Endereço:");
        textFieldEndereco = new JTextField();
        textFieldEndereco.setEditable(false);
        textFieldEndereco.setPreferredSize(new Dimension(300, 30));

        labelEspecifico = new JLabel("Preferência:");
        textFieldEspecifico = new JTextField();
        textFieldEspecifico.setEditable(false);
        textFieldEspecifico.setPreferredSize(new Dimension(300, 30));

        buttonVerAnimais = new JButton("Ver Animais");
        buttonVerProcessos = new JButton("Ver Processos");
        buttonLogout = new JButton("Sair");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2; gbc.anchor = GridBagConstraints.CENTER;
        painelPrincipal.add(labelBoasVindas, gbc);

        gbc.gridwidth = 1; gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0; gbc.gridy = 1;
        painelPrincipal.add(labelNome, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2; painelPrincipal.add(labelEmail, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldEmail, gbc);

        gbc.gridx = 0; gbc.gridy = 3; painelPrincipal.add(labelDocumento, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldDocumento, gbc);

        gbc.gridx = 0; gbc.gridy = 4; painelPrincipal.add(labelTelefone, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldTelefone, gbc);

        gbc.gridx = 0; gbc.gridy = 5; painelPrincipal.add(labelEndereco, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldEndereco, gbc);

        gbc.gridx = 0; gbc.gridy = 6; painelPrincipal.add(labelEspecifico, gbc);
        gbc.gridx = 1; painelPrincipal.add(textFieldEspecifico, gbc);

        gbc.gridx = 0; gbc.gridy = 7; painelPrincipal.add(buttonVerAnimais, gbc);
        gbc.gridx = 1; painelPrincipal.add(buttonVerProcessos, gbc);

        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; painelPrincipal.add(buttonLogout, gbc);
    }
}
