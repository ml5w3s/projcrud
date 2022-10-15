package DAO;
import DTO.PessoaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class PessoaDAO {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    //ArrayList<PessoaDTO> list = new ArrayList<>();
    public void cadastrarPessoa(PessoaDTO objpessoadto){
        String sql="INSERT INTO pessoa (nome) VALUES(?)";
        conn = new ConexaoDAO().conectaBD();
        try{
            pstm=conn.prepareStatement(sql);
            pstm.setString(1,objpessoadto.getNome());
            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Cadastro efetuado com sucesso!");
        }catch(SQLException erro){
            JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+erro);
        }
    }
        public ArrayList<PessoaDTO>getListaPessoa(){
        ArrayList<PessoaDTO> lista = new ArrayList<>();
        conn = new ConexaoDAO().conectaBD();
         String sql="select * from pessoa;";
         try{
             pstm=conn.prepareStatement(sql);
             rs=pstm.executeQuery();
             PessoaDTO pessoa = null;
             while(rs.next()){
                 pessoa = new PessoaDTO();
                 pessoa.setId(rs.getInt("id"));
                 pessoa.setNome(rs.getString("nome"));
                 lista.add(pessoa);
              }
              pstm.close();
         }catch(SQLException ex){
             System.out.println(ex);
         }
         return lista;
    }
        public boolean excluirPessoa(PessoaDTO pessoa) {
            conn = new ConexaoDAO().conectaBD();
            String sql="delete from pessoa where id = ?;";

                try{
                    pstm = conn.prepareStatement(sql);
                    pstm.setInt(1, pessoa.getId());
                    pstm.execute();
                    conn.close();
                    return true;

                }catch (SQLException ex){
                    System.out.println(ex);
                return false;

            }
        }
        public boolean alterar(PessoaDTO pessoa) {
            conn = new ConexaoDAO().conectaBD();
            String sql = "update pessoa set nome = ? where id = ?;";
            
                try  {
                    pstm = conn.prepareStatement(sql);
                    pstm.setString(1, pessoa.getNome());
                    pstm.setInt(2, pessoa.getId());
                    pstm.execute();
                    conn.close();
                    
                    JOptionPane.showMessageDialog(null, "Cadastro atualizado com sucesso!");
                }catch (SQLException ex){
                    JOptionPane.showMessageDialog(null, "FuncionarioDAO cadastrar "+ex);
        
                    return false;
                }
        
                    return true;
            }
        
        public PessoaDTO pesquisar(String id) {
            conn = new ConexaoDAO().conectaBD();
            String sql = "select * from pessoa where id = ?";

        try{
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            PessoaDTO pessoa = null;
            if(rs.next()){
            pessoa = new PessoaDTO();
            pessoa.setNome(rs.getString("nome"));
            pessoa.setId(rs.getInt("id"));            }
            conn.close();
            
            return pessoa;

        }catch (SQLException ex){
            System.out.println(ex);
        
            return null;
        }
    }
}
