package forms;

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

        // Configuração da janela
        setTitle("Sistema de Adoção Animal - Bem-vindo");
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 450);
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
            labelEspecifico.setText("Preferência de Adoção:");
            labelBoasVindas.setText("Bem-vindo, Protetor!");
        } else if ("Adotante".equals(tipoUsuario)) {
            labelEspecifico.setText("Tipo:");
            labelBoasVindas.setText("Bem-vindo, Adotante!");
        }

        // Preencher os campos com dados do usuário
        preencherDadosUsuario();

        // Adicionar action listeners aos botões
        buttonVerAnimais.addActionListener(e -> verAnimais());
        buttonVerProcessos.addActionListener(e -> verProcessos());
        buttonLogout.addActionListener(e -> logout());
    }

    /**
     * Preenche os campos com os dados do usuário logado
     */
    private void preencherDadosUsuario() {
        try {
            // Usar reflexão para acessar os campos do objeto usuário
            Class<?> clazz = usuario.getClass();

            // Campos comuns
            textFieldNome.setText((String) clazz.getMethod("getNome").invoke(usuario));
            textFieldEmail.setText((String) clazz.getMethod("getEmail").invoke(usuario));
            textFieldDocumento.setText((String) clazz.getMethod("getDocumento").invoke(usuario));
            textFieldTelefone.setText((String) clazz.getMethod("getTelefone").invoke(usuario));
            textFieldEndereco.setText((String) clazz.getMethod("getEndereco").invoke(usuario));

            // Campo específico
            if ("Protetor".equals(tipoUsuario)) {
                textFieldEspecifico.setText((String) clazz.getMethod("getPreferenciaAdocao").invoke(usuario));
            } else if ("Adotante".equals(tipoUsuario)) {
                textFieldEspecifico.setText((String) clazz.getMethod("getTipo").invoke(usuario));
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

    /**
     * Método main para testar a tela de boas-vindas
     * (Este método seria usado para desenvolvimento/teste)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Exemplo de uso - substituir por dados reais do login
            // Aqui você passaria o objeto Protetor ou Adotante obtido do login

            // Exemplo com dados mock
            Welcome welcomeFrame = new Welcome("Protetor", criarUsuarioMock("Protetor"));
            welcomeFrame.setVisible(true);
        });
    }

    /**
     * Método auxiliar para criar um usuário mock para teste
     */
    private static Object criarUsuarioMock(String tipo) {
        if ("Protetor".equals(tipo)) {
            // Criar uma instância mock de Protetor
            return new Object() {
                public String getNome() { return "João Silva"; }
                public String getEmail() { return "joao@email.com"; }
                public String getDocumento() { return "123456789"; }
                public String getTelefone() { return "11987654321"; }
                public String getEndereco() { return "Rua A, 123"; }
                public String getPreferenciaAdocao() { return "Cachorros"; }
            };
        } else {
            // Criar uma instância mock de Adotante
            return new Object() {
                public String getNome() { return "Lucas Pereira"; }
                public String getEmail() { return "lucas@email.com"; }
                public String getDocumento() { return "111222333"; }
                public String getTelefone() { return "11432109876"; }
                public String getEndereco() { return "Rua F, 303"; }
                public String getTipo() { return "Individual"; }
            };
        }
    }
}
