package action;

import controller.Action;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Contato;
import persistence.ContatoDAO;

public class GravarContatoAction implements Action {

    public GravarContatoAction() {
    
    }
    
    public void execute(HttpServletRequest request,
            HttpServletResponse response) throws IOException{
    String nome = request.getParameter("txtNome");
        String email = request.getParameter("txtEmail");
        if(nome.equals("") || email.equals((""))){
            response.sendRedirect("index.jsp");
        } else{
            try{
                Contato contato = new Contato(nome, email);
                ContatoDAO.getInstance().save(contato);
                response.sendRedirect("contatoSucesso.jsp");
            } catch(SQLException ex)
            {
                response.sendRedirect("contatoErro.jsp");
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        }
    }
}
