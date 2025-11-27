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

        // Inicializar painel principal
        painelPrincipal = new JPanel(new GridBagLayout());
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
        setLocationRelativeTo(null); // Centraliza na tela

        // Inicializar componentes
        labelBoasVindas = new JLabel("Bem-vindo!");
        labelBoasVindas.setFont(new Font("Segoe UI", Font.BOLD, 18));
        labelBoasVindas.setForeground(Color.BLACK);
        labelBoasVindas.setHorizontalAlignment(SwingConstants.CENTER);

        labelNome = new JLabel("Nome:");
        labelNome.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelNome.setForeground(Color.BLACK);

        textFieldNome = new JTextField();
        textFieldNome.setBackground(Color.WHITE);
        textFieldNome.setEditable(false);
        textFieldNome.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        labelEmail = new JLabel("Email:");
        labelEmail.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelEmail.setForeground(Color.BLACK);

        textFieldEmail = new JTextField();
        textFieldEmail.setBackground(Color.WHITE);
        textFieldEmail.setEditable(false);
        textFieldEmail.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        labelDocumento = new JLabel("Documento:");
        labelDocumento.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelDocumento.setForeground(Color.BLACK);

        textFieldDocumento = new JTextField();
        textFieldDocumento.setBackground(Color.WHITE);
        textFieldDocumento.setEditable(false);
        textFieldDocumento.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        labelTelefone = new JLabel("Telefone:");
        labelTelefone.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelTelefone.setForeground(Color.BLACK);

        textFieldTelefone = new JTextField();
        textFieldTelefone.setBackground(Color.WHITE);
        textFieldTelefone.setEditable(false);
        textFieldTelefone.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        labelEndereco = new JLabel("Endereço:");
        labelEndereco.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelEndereco.setForeground(Color.BLACK);

        textFieldEndereco = new JTextField();
        textFieldEndereco.setBackground(Color.WHITE);
        textFieldEndereco.setEditable(false);
        textFieldEndereco.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        labelEspecifico = new JLabel("Preferência:");
        labelEspecifico.setFont(new Font("Segoe UI", Font.BOLD, 14));
        labelEspecifico.setForeground(Color.BLACK);

        textFieldEspecifico = new JTextField();
        textFieldEspecifico.setBackground(Color.WHITE);
        textFieldEspecifico.setEditable(false);
        textFieldEspecifico.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        buttonVerAnimais = new JButton("Ver Animais");
        buttonVerAnimais.setBackground(new Color(0, 123, 255));
        buttonVerAnimais.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonVerAnimais.setForeground(Color.WHITE);

        buttonVerProcessos = new JButton("Ver Processos");
        buttonVerProcessos.setBackground(new Color(0, 123, 255));
        buttonVerProcessos.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonVerProcessos.setForeground(Color.WHITE);

        buttonLogout = new JButton("Sair");
        buttonLogout.setBackground(Color.RED);
        buttonLogout.setFont(new Font("Segoe UI", Font.BOLD, 14));
        buttonLogout.setForeground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();

        // Adicionar labelBoasVindas
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(20, 10, 10, 40);
        painelPrincipal.add(labelBoasVindas, gbc);

        // Adicionar labelNome
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelNome, gbc);

        // Adicionar textFieldNome
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldNome, gbc);

        // Adicionar labelEmail
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelEmail, gbc);

        // Adicionar textFieldEmail
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldEmail, gbc);

        // Adicionar labelDocumento
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelDocumento, gbc);

        // Adicionar textFieldDocumento
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldDocumento, gbc);

        // Adicionar labelTelefone
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelTelefone, gbc);

        // Adicionar textFieldTelefone
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldTelefone, gbc);

        // Adicionar labelEndereco
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelEndereco, gbc);

        // Adicionar textFieldEndereco
        gbc.gridx = 2;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldEndereco, gbc);

        // Adicionar labelEspecifico
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(10, 40, 10, 10);
        painelPrincipal.add(labelEspecifico, gbc);

        // Adicionar textFieldEspecifico
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 40);
        painelPrincipal.add(textFieldEspecifico, gbc);

        // Adicionar buttonVerAnimais
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelPrincipal.add(buttonVerAnimais, gbc);

        // Adicionar buttonVerProcessos
        gbc.gridx = 3;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        painelPrincipal.add(buttonVerProcessos, gbc);

        // Adicionar buttonLogout
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 20, 40);
        painelPrincipal.add(buttonLogout, gbc);

        // Configurar cor de fundo do painel
        painelPrincipal.setBackground(new Color(160, 160, 160)); // Cinza

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
