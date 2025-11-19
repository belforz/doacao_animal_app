package forms;

import javax.swing.*;
import java.awt.*;
import DAO.ProtetorDAO;
import DAO.AdotanteDAO;
import DAO.AnimalDAO;
import schemas.Protetor;
import schemas.Adotante;
import schemas.Animal;
import java.util.List;

public class Admin extends JFrame {
    private JPanel painelPrincipal;
    private JTabbedPane tabbedPane;
    private JButton buttonLogout;

    // Componentes para Protetor
    private JPanel painelProtetor;
    private JLabel labelIdProtetor;
    private JTextField textFieldIdProtetor;
    private JLabel labelNomeProtetor;
    private JTextField textFieldNomeProtetor;
    private JLabel labelEmailProtetor;
    private JTextField textFieldEmailProtetor;
    private JLabel labelDocumentoProtetor;
    private JTextField textFieldDocumentoProtetor;
    private JLabel labelTelefoneProtetor;
    private JTextField textFieldTelefoneProtetor;
    private JLabel labelEnderecoProtetor;
    private JTextField textFieldEnderecoProtetor;
    private JLabel labelSenhaProtetor;
    private JTextField textFieldSenhaProtetor;
    private JLabel labelTipoProtetor;
    private JTextField textFieldTipoProtetor;
    private JButton buttonCriarProtetor;
    private JButton buttonLerProtetor;
    private JButton buttonAtualizarProtetor;
    private JButton buttonDeletarProtetor;
    private JButton buttonLimparProtetor;

    // Componentes para Adotante
    private JPanel painelAdotante;
    private JLabel labelIdAdotante;
    private JTextField textFieldIdAdotante;
    private JLabel labelNomeAdotante;
    private JTextField textFieldNomeAdotante;
    private JLabel labelEmailAdotante;
    private JTextField textFieldEmailAdotante;
    private JLabel labelDocumentoAdotante;
    private JTextField textFieldDocumentoAdotante;
    private JLabel labelTelefoneAdotante;
    private JTextField textFieldTelefoneAdotante;
    private JLabel labelEnderecoAdotante;
    private JTextField textFieldEnderecoAdotante;
    private JLabel labelSenhaAdotante;
    private JTextField textFieldSenhaAdotante;
    private JLabel labelPreferenciaAdotante;
    private JTextField textFieldPreferenciaAdotante;
    private JButton buttonCriarAdotante;
    private JButton buttonLerAdotante;
    private JButton buttonAtualizarAdotante;
    private JButton buttonDeletarAdotante;
    private JButton buttonLimparAdotante;

    // Componentes para Animal
    private JPanel painelAnimal;
    private JLabel labelIdAnimal;
    private JTextField textFieldIdAnimal;
    private JLabel labelNomeAnimal;
    private JTextField textFieldNomeAnimal;
    private JLabel labelEspecieAnimal;
    private JTextField textFieldEspecieAnimal;
    private JLabel labelRacaAnimal;
    private JTextField textFieldRacaAnimal;
    private JLabel labelTemperamentoAnimal;
    private JTextField textFieldTemperamentoAnimal;
    private JLabel labelHistoricoSaudeAnimal;
    private JTextField textFieldHistoricoSaudeAnimal;
    private JLabel labelDescricaoAnimal;
    private JTextField textFieldDescricaoAnimal;
    private JLabel labelEspecialAnimal;
    private JCheckBox checkBoxEspecialAnimal;
    private JLabel labelIdadeAnimal;
    private JTextField textFieldIdadeAnimal;
    private JLabel labelSexoAnimal;
    private JComboBox<String> comboBoxSexoAnimal;
    private JLabel labelStatusAnimal;
    private JTextField textFieldStatusAnimal;
    private JLabel labelIdProtetorAnimal;
    private JTextField textFieldIdProtetorAnimal;
    private JButton buttonCriarAnimal;
    private JButton buttonLerAnimal;
    private JButton buttonAtualizarAnimal;
    private JButton buttonDeletarAnimal;
    private JButton buttonLimparAnimal;

    public Admin() {
        // Inicializar componentes
        initComponents();

        // Configuração da janela
        setTitle("Sistema de Adoção Animal - Administração");
        setContentPane(painelPrincipal);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setLocationRelativeTo(null); // Centraliza na tela

        // Configurar cor de fundo
        painelPrincipal.setBackground(new Color(160, 160, 160)); // Cinza

        // Adicionar action listeners
        buttonCriarProtetor.addActionListener(e -> {
            try {
                String nome = textFieldNomeProtetor.getText().trim();
                String email = textFieldEmailProtetor.getText().trim();
                String documento = textFieldDocumentoProtetor.getText().trim();
                String telefone = textFieldTelefoneProtetor.getText().trim();
                String senha = textFieldSenhaProtetor.getText().trim();
                String endereco = textFieldEnderecoProtetor.getText().trim();
                String tipo = textFieldTipoProtetor.getText().trim();

                if (nome.isEmpty() || email.isEmpty() || documento.isEmpty() || telefone.isEmpty() || senha.isEmpty() || endereco.isEmpty() || tipo.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                ProtetorDAO dao = new ProtetorDAO();
                Protetor p = new Protetor(null, nome, senha, endereco, email, documento, telefone, tipo);
                dao.create(p);
                JOptionPane.showMessageDialog(this, "Protetor criado com sucesso!");
                textFieldIdProtetor.setText(String.valueOf(p.getId()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLerProtetor.addActionListener(e -> {
            try {
                ProtetorDAO dao = new ProtetorDAO();
                String idText = textFieldIdProtetor.getText().trim();
                if (idText.isEmpty()) {
                    List<Protetor> list = dao.readAll();
                    StringBuilder sb = new StringBuilder("Lista de Protetores:\n");
                    for (Protetor p : list) {
                        sb.append("ID: ").append(p.getId()).append(" - Nome: ").append(p.getNome()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    int id = Integer.parseInt(idText);
                    Protetor p = dao.read(id);
                    if (p != null) {
                        textFieldIdProtetor.setText(String.valueOf(p.getId()));
                        textFieldNomeProtetor.setText(p.getNome());
                        textFieldEmailProtetor.setText(p.getEmail());
                        textFieldDocumentoProtetor.setText(p.getDocumento());
                        textFieldTelefoneProtetor.setText(p.getTelefone());
                        textFieldEnderecoProtetor.setText(p.getEndereco());
                        textFieldSenhaProtetor.setText(p.getSenha());
                        textFieldTipoProtetor.setText(p.getTipo());
                        JOptionPane.showMessageDialog(this, "Protetor carregado!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Protetor não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonAtualizarProtetor.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdProtetor.getText());
                ProtetorDAO dao = new ProtetorDAO();
                Protetor p = new Protetor(id, textFieldNomeProtetor.getText(), textFieldSenhaProtetor.getText(), textFieldEnderecoProtetor.getText(), textFieldEmailProtetor.getText(), textFieldDocumentoProtetor.getText(), textFieldTelefoneProtetor.getText(), textFieldTipoProtetor.getText());
                dao.update(p);
                JOptionPane.showMessageDialog(this, "Protetor atualizado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonDeletarProtetor.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdProtetor.getText());
                ProtetorDAO dao = new ProtetorDAO();
                dao.delete(id);
                JOptionPane.showMessageDialog(this, "Protetor deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLimparProtetor.addActionListener(e -> {
            textFieldIdProtetor.setText("");
            textFieldNomeProtetor.setText("");
            textFieldEmailProtetor.setText("");
            textFieldDocumentoProtetor.setText("");
            textFieldTelefoneProtetor.setText("");
            textFieldEnderecoProtetor.setText("");
            textFieldSenhaProtetor.setText("");
            textFieldTipoProtetor.setText("");
        });

        buttonCriarAdotante.addActionListener(e -> {
            try {
                String nome = textFieldNomeAdotante.getText().trim();
                String email = textFieldEmailAdotante.getText().trim();
                String documento = textFieldDocumentoAdotante.getText().trim();
                String telefone = textFieldTelefoneAdotante.getText().trim();
                String senha = textFieldSenhaAdotante.getText().trim();
                String endereco = textFieldEnderecoAdotante.getText().trim();
                String preferencia = textFieldPreferenciaAdotante.getText().trim();

                if (nome.isEmpty() || email.isEmpty() || documento.isEmpty() || telefone.isEmpty() || senha.isEmpty() || endereco.isEmpty() || preferencia.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                AdotanteDAO dao = new AdotanteDAO();
                Adotante a = new Adotante(null, nome, senha, endereco, email, documento, telefone, preferencia);
                dao.create(a);
                JOptionPane.showMessageDialog(this, "Adotante criado com sucesso!");
                textFieldIdAdotante.setText(String.valueOf(a.getId()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLerAdotante.addActionListener(e -> {
            try {
                AdotanteDAO dao = new AdotanteDAO();
                String idText = textFieldIdAdotante.getText().trim();
                if (idText.isEmpty()) {
                    List<Adotante> list = dao.readAll();
                    StringBuilder sb = new StringBuilder("Lista de Adotantes:\n");
                    for (Adotante a : list) {
                        sb.append("ID: ").append(a.getId()).append(" - Nome: ").append(a.getNome()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    int id = Integer.parseInt(idText);
                    Adotante a = dao.read(id);
                    if (a != null) {
                        textFieldIdAdotante.setText(String.valueOf(a.getId()));
                        textFieldNomeAdotante.setText(a.getNome());
                        textFieldEmailAdotante.setText(a.getEmail());
                        textFieldDocumentoAdotante.setText(a.getDocumento());
                        textFieldTelefoneAdotante.setText(a.getTelefone());
                        textFieldEnderecoAdotante.setText(a.getEndereco());
                        textFieldSenhaAdotante.setText(a.getSenha());
                        textFieldPreferenciaAdotante.setText(a.getPreferenciaAdocao());
                        JOptionPane.showMessageDialog(this, "Adotante carregado!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Adotante não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonAtualizarAdotante.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdAdotante.getText());
                AdotanteDAO dao = new AdotanteDAO();
                Adotante a = new Adotante(id, textFieldNomeAdotante.getText(), textFieldSenhaAdotante.getText(), textFieldEnderecoAdotante.getText(), textFieldEmailAdotante.getText(), textFieldDocumentoAdotante.getText(), textFieldTelefoneAdotante.getText(), textFieldPreferenciaAdotante.getText());
                dao.update(a);
                JOptionPane.showMessageDialog(this, "Adotante atualizado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonDeletarAdotante.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdAdotante.getText());
                AdotanteDAO dao = new AdotanteDAO();
                dao.delete(id);
                JOptionPane.showMessageDialog(this, "Adotante deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLimparAdotante.addActionListener(e -> {
            textFieldIdAdotante.setText("");
            textFieldNomeAdotante.setText("");
            textFieldEmailAdotante.setText("");
            textFieldDocumentoAdotante.setText("");
            textFieldTelefoneAdotante.setText("");
            textFieldEnderecoAdotante.setText("");
            textFieldSenhaAdotante.setText("");
            textFieldPreferenciaAdotante.setText("");
        });

        buttonCriarAnimal.addActionListener(e -> {
            try {
                String especie = textFieldEspecieAnimal.getText().trim();
                String raca = textFieldRacaAnimal.getText().trim();
                String temperamento = textFieldTemperamentoAnimal.getText().trim();
                String historicoSaude = textFieldHistoricoSaudeAnimal.getText().trim();
                String nome = textFieldNomeAnimal.getText().trim();
                String descricao = textFieldDescricaoAnimal.getText().trim();
                String idadeText = textFieldIdadeAnimal.getText().trim();
                String status = textFieldStatusAnimal.getText().trim();
                String idProtetorText = textFieldIdProtetorAnimal.getText().trim();

                if (especie.isEmpty() || raca.isEmpty() || temperamento.isEmpty() || historicoSaude.isEmpty() || nome.isEmpty() || descricao.isEmpty() || idadeText.isEmpty() || status.isEmpty() || idProtetorText.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int idade = Integer.parseInt(idadeText);
                int idProtetor = Integer.parseInt(idProtetorText);

                AnimalDAO dao = new AnimalDAO();
                Animal a = new Animal(null, especie, raca, temperamento, historicoSaude, nome, descricao, checkBoxEspecialAnimal.isSelected(), idade, ((String)comboBoxSexoAnimal.getSelectedItem()).charAt(0), status, idProtetor);
                dao.create(a);
                JOptionPane.showMessageDialog(this, "Animal criado com sucesso!");
                textFieldIdAnimal.setText(String.valueOf(a.getId()));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLerAnimal.addActionListener(e -> {
            try {
                AnimalDAO dao = new AnimalDAO();
                String idText = textFieldIdAnimal.getText().trim();
                if (idText.isEmpty()) {
                    List<Animal> list = dao.readAll();
                    StringBuilder sb = new StringBuilder("Lista de Animais:\n");
                    for (Animal a : list) {
                        sb.append("ID: ").append(a.getId()).append(" - Nome: ").append(a.getNome()).append("\n");
                    }
                    JOptionPane.showMessageDialog(this, sb.toString());
                } else {
                    int id = Integer.parseInt(idText);
                    Animal a = dao.read(id);
                    if (a != null) {
                        textFieldIdAnimal.setText(String.valueOf(a.getId()));
                        textFieldNomeAnimal.setText(a.getNome());
                        textFieldEspecieAnimal.setText(a.getEspecie());
                        textFieldRacaAnimal.setText(a.getRaca());
                        textFieldTemperamentoAnimal.setText(a.getTemperamento());
                        textFieldHistoricoSaudeAnimal.setText(a.getHistoricoSaude());
                        textFieldDescricaoAnimal.setText(a.getDescricao());
                        checkBoxEspecialAnimal.setSelected(a.getEsEspecial());
                        textFieldIdadeAnimal.setText(String.valueOf(a.getIdade()));
                        comboBoxSexoAnimal.setSelectedItem(String.valueOf(a.getSexo()));
                        textFieldStatusAnimal.setText(a.getStatus());
                        textFieldIdProtetorAnimal.setText(String.valueOf(a.getProtetor().getId()));
                        JOptionPane.showMessageDialog(this, "Animal carregado!");
                    } else {
                        JOptionPane.showMessageDialog(this, "Animal não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonAtualizarAnimal.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdAnimal.getText());
                AnimalDAO dao = new AnimalDAO();
                Animal a = new Animal(id, textFieldEspecieAnimal.getText(), textFieldRacaAnimal.getText(), textFieldTemperamentoAnimal.getText(), textFieldHistoricoSaudeAnimal.getText(), textFieldNomeAnimal.getText(), textFieldDescricaoAnimal.getText(), checkBoxEspecialAnimal.isSelected(), Integer.parseInt(textFieldIdadeAnimal.getText()), ((String)comboBoxSexoAnimal.getSelectedItem()).charAt(0), textFieldStatusAnimal.getText(), Integer.parseInt(textFieldIdProtetorAnimal.getText()));
                dao.update(a);
                JOptionPane.showMessageDialog(this, "Animal atualizado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonDeletarAnimal.addActionListener(e -> {
            try {
                int id = Integer.parseInt(textFieldIdAnimal.getText());
                AnimalDAO dao = new AnimalDAO();
                dao.delete(id);
                JOptionPane.showMessageDialog(this, "Animal deletado com sucesso!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonLimparAnimal.addActionListener(e -> {
            textFieldIdAnimal.setText("");
            textFieldNomeAnimal.setText("");
            textFieldEspecieAnimal.setText("");
            textFieldRacaAnimal.setText("");
            textFieldTemperamentoAnimal.setText("");
            textFieldHistoricoSaudeAnimal.setText("");
            textFieldDescricaoAnimal.setText("");
            checkBoxEspecialAnimal.setSelected(false);
            textFieldIdadeAnimal.setText("");
            comboBoxSexoAnimal.setSelectedIndex(0);
            textFieldStatusAnimal.setText("");
            textFieldIdProtetorAnimal.setText("");
        });

        buttonLogout.addActionListener(e -> logout());
    }

    private void logout() {
        int resposta = JOptionPane.showConfirmDialog(this,
            "Deseja realmente sair?",
            "Confirmação",
            JOptionPane.YES_NO_OPTION);

        if (resposta == JOptionPane.YES_OPTION) {
            this.dispose();
            Login loginFrame = new Login();
            loginFrame.setVisible(true);
        }
    }

    private void initComponents() {
        painelPrincipal = new JPanel(new BorderLayout());

        tabbedPane = new JTabbedPane();

        // Painel Protetor
        painelProtetor = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        labelIdProtetor = new JLabel("ID:");
        textFieldIdProtetor = new JTextField();
        textFieldIdProtetor.setPreferredSize(new Dimension(200, 25));
        textFieldIdProtetor.setEnabled(true); // ID editável

        labelNomeProtetor = new JLabel("Nome:");
        textFieldNomeProtetor = new JTextField();
        textFieldNomeProtetor.setPreferredSize(new Dimension(200, 25));

        labelEmailProtetor = new JLabel("Email:");
        textFieldEmailProtetor = new JTextField();
        textFieldEmailProtetor.setPreferredSize(new Dimension(200, 25));

        labelDocumentoProtetor = new JLabel("Documento:");
        textFieldDocumentoProtetor = new JTextField();
        textFieldDocumentoProtetor.setPreferredSize(new Dimension(200, 25));

        labelTelefoneProtetor = new JLabel("Telefone:");
        textFieldTelefoneProtetor = new JTextField();
        textFieldTelefoneProtetor.setPreferredSize(new Dimension(200, 25));

        labelEnderecoProtetor = new JLabel("Endereço:");
        textFieldEnderecoProtetor = new JTextField();
        textFieldEnderecoProtetor.setPreferredSize(new Dimension(200, 25));

        labelSenhaProtetor = new JLabel("Senha:");
        textFieldSenhaProtetor = new JTextField();
        textFieldSenhaProtetor.setPreferredSize(new Dimension(200, 25));

        labelTipoProtetor = new JLabel("Tipo:");
        textFieldTipoProtetor = new JTextField();
        textFieldTipoProtetor.setPreferredSize(new Dimension(200, 25));

        buttonCriarProtetor = new JButton("Criar");
        buttonLerProtetor = new JButton("Ler");
        buttonAtualizarProtetor = new JButton("Atualizar");
        buttonDeletarProtetor = new JButton("Deletar");
        buttonLimparProtetor = new JButton("Limpar");

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painelProtetor.add(labelIdProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldIdProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 1; painelProtetor.add(labelNomeProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldNomeProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 2; painelProtetor.add(labelEmailProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldEmailProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 3; painelProtetor.add(labelDocumentoProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldDocumentoProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 4; painelProtetor.add(labelTelefoneProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldTelefoneProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 5; painelProtetor.add(labelEnderecoProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldEnderecoProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 6; painelProtetor.add(labelSenhaProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldSenhaProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 7; painelProtetor.add(labelTipoProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(textFieldTipoProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 8; painelProtetor.add(buttonCriarProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(buttonLerProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 9; painelProtetor.add(buttonAtualizarProtetor, gbc);
        gbc.gridx = 1; painelProtetor.add(buttonDeletarProtetor, gbc);

        gbc.gridx = 0; gbc.gridy = 10; painelProtetor.add(buttonLimparProtetor, gbc);

        tabbedPane.addTab("Protetor", painelProtetor);

        // Painel Adotante
        painelAdotante = new JPanel(new GridBagLayout());

        labelIdAdotante = new JLabel("ID:");
        textFieldIdAdotante = new JTextField();
        textFieldIdAdotante.setPreferredSize(new Dimension(200, 25));
        textFieldIdAdotante.setEnabled(true);

        labelNomeAdotante = new JLabel("Nome:");
        textFieldNomeAdotante = new JTextField();
        textFieldNomeAdotante.setPreferredSize(new Dimension(200, 25));

        labelEmailAdotante = new JLabel("Email:");
        textFieldEmailAdotante = new JTextField();
        textFieldEmailAdotante.setPreferredSize(new Dimension(200, 25));

        labelDocumentoAdotante = new JLabel("Documento:");
        textFieldDocumentoAdotante = new JTextField();
        textFieldDocumentoAdotante.setPreferredSize(new Dimension(200, 25));

        labelTelefoneAdotante = new JLabel("Telefone:");
        textFieldTelefoneAdotante = new JTextField();
        textFieldTelefoneAdotante.setPreferredSize(new Dimension(200, 25));

        labelEnderecoAdotante = new JLabel("Endereço:");
        textFieldEnderecoAdotante = new JTextField();
        textFieldEnderecoAdotante.setPreferredSize(new Dimension(200, 25));

        labelSenhaAdotante = new JLabel("Senha:");
        textFieldSenhaAdotante = new JTextField();
        textFieldSenhaAdotante.setPreferredSize(new Dimension(200, 25));

        labelPreferenciaAdotante = new JLabel("Preferência:");
        textFieldPreferenciaAdotante = new JTextField();
        textFieldPreferenciaAdotante.setPreferredSize(new Dimension(200, 25));

        buttonCriarAdotante = new JButton("Criar");
        buttonLerAdotante = new JButton("Ler");
        buttonAtualizarAdotante = new JButton("Atualizar");
        buttonDeletarAdotante = new JButton("Deletar");
        buttonLimparAdotante = new JButton("Limpar");

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painelAdotante.add(labelIdAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldIdAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 1; painelAdotante.add(labelNomeAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldNomeAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 2; painelAdotante.add(labelEmailAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldEmailAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 3; painelAdotante.add(labelDocumentoAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldDocumentoAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 4; painelAdotante.add(labelTelefoneAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldTelefoneAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 5; painelAdotante.add(labelEnderecoAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldEnderecoAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 6; painelAdotante.add(labelSenhaAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldSenhaAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 7; painelAdotante.add(labelPreferenciaAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(textFieldPreferenciaAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 8; painelAdotante.add(buttonCriarAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(buttonLerAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 9; painelAdotante.add(buttonAtualizarAdotante, gbc);
        gbc.gridx = 1; painelAdotante.add(buttonDeletarAdotante, gbc);

        gbc.gridx = 0; gbc.gridy = 10; painelAdotante.add(buttonLimparAdotante, gbc);

        tabbedPane.addTab("Adotante", painelAdotante);

        // Painel Animal
        painelAnimal = new JPanel(new GridBagLayout());

        labelIdAnimal = new JLabel("ID:");
        textFieldIdAnimal = new JTextField();
        textFieldIdAnimal.setPreferredSize(new Dimension(200, 25));
        textFieldIdAnimal.setEnabled(true);

        labelNomeAnimal = new JLabel("Nome:");
        textFieldNomeAnimal = new JTextField();
        textFieldNomeAnimal.setPreferredSize(new Dimension(200, 25));

        labelEspecieAnimal = new JLabel("Espécie:");
        textFieldEspecieAnimal = new JTextField();
        textFieldEspecieAnimal.setPreferredSize(new Dimension(200, 25));

        labelRacaAnimal = new JLabel("Raça:");
        textFieldRacaAnimal = new JTextField();
        textFieldRacaAnimal.setPreferredSize(new Dimension(200, 25));

        labelTemperamentoAnimal = new JLabel("Temperamento:");
        textFieldTemperamentoAnimal = new JTextField();
        textFieldTemperamentoAnimal.setPreferredSize(new Dimension(200, 25));

        labelHistoricoSaudeAnimal = new JLabel("Histórico Saúde:");
        textFieldHistoricoSaudeAnimal = new JTextField();
        textFieldHistoricoSaudeAnimal.setPreferredSize(new Dimension(200, 25));

        labelDescricaoAnimal = new JLabel("Descrição:");
        textFieldDescricaoAnimal = new JTextField();
        textFieldDescricaoAnimal.setPreferredSize(new Dimension(200, 25));

        labelEspecialAnimal = new JLabel("Especial:");
        checkBoxEspecialAnimal = new JCheckBox();

        labelIdadeAnimal = new JLabel("Idade:");
        textFieldIdadeAnimal = new JTextField();
        textFieldIdadeAnimal.setPreferredSize(new Dimension(200, 25));

        labelSexoAnimal = new JLabel("Sexo:");
        comboBoxSexoAnimal = new JComboBox<>(new String[]{"M", "F"});

        labelStatusAnimal = new JLabel("Status:");
        textFieldStatusAnimal = new JTextField();
        textFieldStatusAnimal.setPreferredSize(new Dimension(200, 25));

        labelIdProtetorAnimal = new JLabel("ID Protetor:");
        textFieldIdProtetorAnimal = new JTextField();
        textFieldIdProtetorAnimal.setPreferredSize(new Dimension(200, 25));

        buttonCriarAnimal = new JButton("Criar");
        buttonLerAnimal = new JButton("Ler");
        buttonAtualizarAnimal = new JButton("Atualizar");
        buttonDeletarAnimal = new JButton("Deletar");
        buttonLimparAnimal = new JButton("Limpar");

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
        painelAnimal.add(labelIdAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldIdAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 1; painelAnimal.add(labelNomeAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldNomeAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 2; painelAnimal.add(labelEspecieAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldEspecieAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 3; painelAnimal.add(labelRacaAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldRacaAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 4; painelAnimal.add(labelTemperamentoAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldTemperamentoAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 5; painelAnimal.add(labelHistoricoSaudeAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldHistoricoSaudeAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 6; painelAnimal.add(labelDescricaoAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldDescricaoAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 7; painelAnimal.add(labelEspecialAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(checkBoxEspecialAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 8; painelAnimal.add(labelIdadeAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldIdadeAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 9; painelAnimal.add(labelSexoAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(comboBoxSexoAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 10; painelAnimal.add(labelStatusAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldStatusAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 11; painelAnimal.add(labelIdProtetorAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(textFieldIdProtetorAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 12; painelAnimal.add(buttonCriarAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(buttonLerAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 13; painelAnimal.add(buttonAtualizarAnimal, gbc);
        gbc.gridx = 1; painelAnimal.add(buttonDeletarAnimal, gbc);

        gbc.gridx = 0; gbc.gridy = 14; painelAnimal.add(buttonLimparAnimal, gbc);

        tabbedPane.addTab("Animal", painelAnimal);

        painelPrincipal.add(tabbedPane, BorderLayout.CENTER);

        buttonLogout = new JButton("Sair");
        painelPrincipal.add(buttonLogout, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Admin adminFrame = new Admin();
            adminFrame.setVisible(true);
        });
    }
}
