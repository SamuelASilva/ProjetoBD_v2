import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import model.dao.PessoaDAO;
import model.entity.Pessoa;
import model.dao.ProdutoDAO;
import model.entity.Produto;

public class App {

	private static final Produto Produto = null;

    public static String leString(String msg) {
        String valor = JOptionPane.showInputDialog(null, msg);
        return valor;
    }

    public static int menu() {
        Scanner teclado = new Scanner(System.in);  
        System.out.println("MENU");
        System.out.println("1- Inserir");
        System.out.println("2- Listar todos");
        System.out.println("3- Listar por id");
        System.out.println("4- Excluir por id");
        System.out.println("5- Atualizar");
        System.out.println("6- Adicionar Veiculo");
        System.out.println("7- Lista Veículo por ID");
        System.out.println("8- Consulta por placa");
        System.out.println("9- Excluir Veiculo por id");
        System.out.println("10- Atualizar Veículo");
        System.out.println("11- Sair");
        System.out.print("Digite: ");
        return teclado.nextInt();
    }

    public static void metodoInserirveiculo() {
        String numeroChassi = leString("Digite o Chassi do veículo");
        String placa = leString("Digite a placa do veículo");
        String modelo = leString("Digite a modelo do veículo");
        String marca = leString("Digite a marca do veículo");
        String valor = leString("Digite o valor do veículo");
        double conversao = Double.parseDouble(valor);
        ProdutoDAO produtoDAO = new ProdutoDAO();
        Produto produto = new Produto(numeroChassi, placa, modelo, marca, conversao);
        produtoDAO.inserirproduto(produto);
    }

    public static void metodoInserir() {
        String nome = leString("Digite nome");
        String email = leString("Digite e-mail");
        Pessoa pessoa = new Pessoa(nome,email);
        PessoaDAO pessoaDAO = new PessoaDAO();
        pessoaDAO.inserir(pessoa);        
    }

    public static void metodoConsultarTodos() {
        List<Pessoa> registros = new PessoaDAO().consultarTodos();
        if (!registros.isEmpty()){
            String saida = "";
            saida += "id\tnome\temail\n";
            for (int i = 0; i < registros.size(); i++) {
                Pessoa p = registros.get(i);
                saida += p.getId()+"\t";
                saida = saida + p.getNome()+"\t";
                saida += p.getEmail()+"\n";                
            }
            JOptionPane.showMessageDialog(null, new JTextArea(saida));
        }else{
            System.out.println("Nao tem registros");
        }
    }
    
    public static void metodoExcluir() {
        String tmp = leString("Digite id para excluir");
        int id = Integer.parseInt(tmp); // converte pra int
        PessoaDAO dao = new PessoaDAO();
        if (dao.excluir(id)){
            JOptionPane.showMessageDialog(null, "Registro " +id + " exluido");
        }else{
            JOptionPane.showMessageDialog(null, "Registro " +id + " nao existe");
        }
    }
    public static void excluirproduto() {
        String tmpprod = leString("Digite a id para excluir");
        int id = Integer.parseInt(tmpprod); //converte pra int
        ProdutoDAO prod = new ProdutoDAO();
        if( prod.excluir(id)) {
            JOptionPane.showMessageDialog(null, "Registro " + id + " excluido");
        } else {
            JOptionPane.showMessageDialog(null,"Registro " + id + " não existe");
        }
    }
    /**
     * 
     */
    public static void atualizarproduto() {
        String placa = leString("Digite a placa");
        if (placa != null) {
            String numeroChassi = leString("Digite o novo Chassi");
            String placa1 = leString("Digite a nova placa:");
            String modelo = leString("Digite o novo modelo:");
            String marca = leString("Digite a nova marca:");
            String valor = leString("Digite o novo valor:");
    
            Produto produto = new Produto(numeroChassi, placa1, modelo, marca, Double.parseDouble(valor));
    
            ProdutoDAO pDAO = new ProdutoDAO();
            System.out.println(pDAO.atualizar(produto));
        }
    }
    public static void main(String[] args) {
        int op;
        do{
            op = menu();
            switch (op){
                case 1:
                    metodoInserir();
                    break;
                case 2:
                    metodoConsultarTodos();
                    break;
                case 3:
                    String idStr = leString("Digite id");
                    // converter de String para int
                    int id = Integer.parseInt(idStr);
                    PessoaDAO dao = new PessoaDAO();
                    Pessoa pess = dao.consultar(id);
                    String saida;
                    if (pess != null){
                        saida = "id\tnome\temail\n";
                        saida += pess.getId()+"\t";
                        saida = saida + pess.getNome()+"\t";
                        saida += pess.getEmail()+"\n"; 
                    }else{
                        saida = "Registro nao foi localizado";
                    }
                    JOptionPane.showMessageDialog(null, new JTextArea(saida));
                    break;
                case 4:
                    metodoExcluir();
                    break;
                case 5:
                    Pessoa p = new Pessoa("Tiririca", "fiorentina@email.com");
                    p.setId(10);
                    PessoaDAO daa = new PessoaDAO();
                    System.out.println(daa.atualizar(p));

                    break;
                case 6:
                    metodoInserirveiculo();
                    break;
                case 7:
                    String idSt = leString("Digite id");
                    // Converter de String para int
                    int idprod = Integer.parseInt(idSt);
                    ProdutoDAO daoprod = new ProdutoDAO();
                    Produto prod = daoprod.consultarid(idprod);
                    if (prod != null) {
                        saida = "id\tnumeroChassi\tplaca\tmodelo\tmarca\tvalor\n";
                        saida = prod.getId()+ "\t";
                        saida += prod.getnumeroChassi()+"\t";
                        saida += prod.getplaca()+"\t";
                        saida += prod.getmodelo()+"\t";
                        saida += prod.getmarca()+"\t";
                        saida += prod.getvalor()+"\t";
                    }else {
                        saida = "Registro não localizado";
                    }
                    JOptionPane.showMessageDialog(null, new JTextArea(saida));
                    break;
                case 8:
                    String idStplaca = leString("Digite id");
                    ProdutoDAO daoplaca = new ProdutoDAO();
                    Produto placa = daoplaca.consultarplaca(idStplaca);
                    if (placa != null) {
                        saida = "id\tnumeroChassi\tplaca\tmodelo\tmarca\tvalor\n";
                        saida = placa.getId()+ "\t";
                        saida += placa.getnumeroChassi()+"\t";
                        saida += placa.getplaca()+"\t";
                        saida += placa.getmodelo()+"\t";
                        saida += placa.getmarca()+"\t";
                        saida += placa.getvalor()+"\t";
                    }else {
                        saida = "Registro não localizado";
                    }
                    JOptionPane.showMessageDialog(null, new JTextArea(saida));
                    break;
                case 9: 
                    excluirproduto();
                    break;
                case 10:
                    atualizarproduto();
                    break;
                case 11:
                    System.out.println("Saindo");
                    break;
                default:
                    System.out.println("Opcao invalida");
            }
        }while(op!=11);
    }
}