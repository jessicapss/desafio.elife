<%@page import="br.com.elife.jdbc.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import="br.com.elife.jdbc.teste.*"
    import="java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>Teste</title>
        <link href="resource/css/demo_page.css" rel="stylesheet" type="text/css" />
        <link href="resource/css/demo_table.css" rel="stylesheet" type="text/css" />
        <link href="resource/css/demo_table_jui.css" rel="stylesheet" type="text/css" />
        <link href="resource/css/jquery-ui-1.10.3.css" rel="stylesheet" type="text/css" media="all" />
        
        <script src="resource/js/jquery-1.10.2.min.js" type="text/javascript"></script>
        <script src="resource/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        $(document).ready(function () {
            $("#users").dataTable({
                "sPaginationType": "full_numbers",
                "bJQueryUI": true
            });
        });
        </script>
    
    </head>
    <body id="dt_example">
        <div id="container">
            <div id="demo_jui">
                <table id="users" class="display">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Endereço</th>
                            <th>País</th>
                            <th>Data Nascimento</th>
                            <th>Email</th>
                        </tr>
                    </thead>
                    <tbody>
                      <% for(User c: UsersTest.getUsers()){ %>
                    <tr>
                         <td><%=c.getNome()%></td>
                         <td><%=c.getEndereco()%></td>
                         <td><%=c.getPais()%></td>
                         <%
                         DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
                         String data = fmt.format(c.getDataNascimento().getTime());
                         %>
                         <td><%=data %></td>
                         <td><%=c.getEmail()%></td>
                    </tr>
                <% } %>
                    </tbody>
                </table>
         </div>
        </div> 
    </body>
</html>