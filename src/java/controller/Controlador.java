package controller;

import config.GenerarSerie;
import config.Imprimir;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modeloDTO.*;
import modeloDAO.*;
import sendemail.SendEmailUsingGMailSMTP;

public class Controlador extends HttpServlet {

    Empleado em = new Empleado();
    EmpleadoDAO edao = new EmpleadoDAO();
    Cliente c = new Cliente();
    ClienteDAO cdao = new ClienteDAO();
    Producto p = new Producto();
    ProductoDAO pdao = new ProductoDAO();
    int ide;
    int idc;
    int idp;

    Venta v = new Venta();
    List<Venta> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;

    String numeroserie = "";
    VentaDAO vdao = new VentaDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        Empleado usuario = (Empleado) session.getAttribute("usuario");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Empleado")) {
            switch (accion) {
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    String user = request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    String user1 = request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List<Cliente> lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    c.setDni(dni);
                    c.setNom(nom);
                    c.setDir(tel);
                    c.setEs(est);
                    cdao.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = cdao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    c.setDni(dni1);
                    c.setNom(nom1);
                    c.setDir(tel1);
                    c.setEs(est1);
                    c.setId(idc);
                    cdao.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List productos = pdao.listar();
                    request.setAttribute("productos", productos);
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    double pre = Double.parseDouble(request.getParameter("txtNombres"));
                    int st = Integer.parseInt(request.getParameter("txtTel"));
                    String est = request.getParameter("txtEstado");
                    p.setNom(dni);
                    p.setPre(pre);
                    p.setStock(st);
                    p.setEstado(est);
                    pdao.agregar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto pr = pdao.listarId(idp);
                    request.setAttribute("producto", pr);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    double pre1 = Double.parseDouble(request.getParameter("txtNombres"));
                    int st1 = Integer.parseInt(request.getParameter("txtTel"));
                    String est1 = request.getParameter("txtEstado");
                    p.setNom(dni1);
                    p.setPre(pre1);
                    p.setStock(st1);
                    p.setEstado(est1);
                    p.setId(idp);
                    pdao.actualizar(p);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            
        }
        if (menu.equals("NuevaVenta")) {
            switch (accion) {
                case "BuscarCliente":
                    String dni = request.getParameter("codigocliente");
                    c.setDni(dni);
                    c = cdao.buscar(dni);
                    request.setAttribute("c", c);
                    request.setAttribute("nserie", numeroserie);
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    break;
                case "BuscarProducto":
                    int id = Integer.parseInt(request.getParameter("codigoproducto"));
                    p = pdao.listarId(id);
                    request.setAttribute("c", c);
                    request.setAttribute("producto", p);
                    request.setAttribute("lista", lista);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("nserie", numeroserie);
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    break;
                case "Agregar":
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("c", c);
                    totalPagar = 0.0;
                    item = item + 1;
                    cod = p.getId();
                    descripcion = request.getParameter("nomproducto");
                    precio = Double.parseDouble(request.getParameter("precio"));
                    cant = Integer.parseInt(request.getParameter("cant"));
                    subtotal = precio * cant;
                    v = new Venta();
                    v.setItem(item);
                    v.setIdproducto(cod);
                    v.setDescripcionP(descripcion);
                    v.setPrecio(precio);
                    v.setCantidad(cant);
                    v.setSubtotal(subtotal);

                    boolean exist = false;
                    int count = 0;
                    int position = 0;
                    for (Venta venta : lista) {
                        if (venta.getIdproducto().equals(cod)) {
                            exist = true;
                            position = count;
                        }
                        count++;
                    }
                    if (exist) {
                        lista.get(position).setCantidad(lista.get(position).getCantidad() + 1);
                    } else {
                        lista.add(v);
                    }
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    break;
                case "delete":
                    int idpd = Integer.parseInt(request.getParameter("id"));
                    for (int i = 0; i < lista.size(); i++) {
                        if (lista.get(i).getIdproducto().equals(idpd)) {
                            lista.remove(i);
                        }
                    }
                    totalPagar = 0.0;
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                    request.setAttribute("nserie", numeroserie);
                    request.setAttribute("c", c);
                    request.setAttribute("totalpagar", totalPagar);
                    request.setAttribute("lista", lista);
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                    break;
                case "updateCant":
                    try {
                    int idpc = Integer.parseInt(request.getParameter("id"));
                    int canti = Integer.parseInt(request.getParameter("cantidad"));
                    if (idpc != 0 && canti != 0) {
                        for (int i = 0; i < lista.size(); i++) {
                            if (lista.get(i).getIdproducto().equals(idpc)) {
                                lista.get(i).setCantidad(canti);
                            }
                        }
                    }
                    totalPagar = 0.0;
                    for (int i = 0; i < lista.size(); i++) {
                        totalPagar = totalPagar + lista.get(i).getSubtotal();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error:" + e);
                }
                request.setAttribute("nserie", numeroserie);
                request.setAttribute("c", c);
                request.setAttribute("totalpagar", totalPagar);
                request.setAttribute("lista", lista);
                session.setAttribute("usuario", usuario);
                request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
                break;

                case "GenerarVenta":
                    //Actualizacion del Stock
                    for (int i = 0; i < lista.size(); i++) {
                        Producto pr = new Producto();
                        int cantidad = lista.get(i).getCantidad();
                        int idproducto = lista.get(i).getIdproducto();
                        ProductoDAO aO = new ProductoDAO();
                        pr = aO.buscar(idproducto);
                        int sac = pr.getStock() - cantidad;
                        aO.actualizarstock(idproducto, sac);
                    }
                    //Guardar Venta
                    v.setIdcliente(c.getId());
                    v.setIdempleado(2);
                    v.setNumserie(numeroserie);
                    v.setFecha("2019-06-14");
                    v.setMonto(totalPagar);
                    v.setEstado("1");
                    int r = vdao.guardarVenta(v);
                    //Guardar Detalle ventas
                    int idv = Integer.parseInt(vdao.IdVentas());
                    for (int i = 0; i < lista.size(); i++) {
                        v = new Venta();
                        v.setId(idv);
                        v.setIdproducto(lista.get(i).getIdproducto());
                        v.setCantidad(lista.get(i).getCantidad());
                        v.setPrecio(lista.get(i).getPrecio());
                        r = vdao.guardarDetalleventas(v);
                    }

                    Imprimir.imprimirDocumento(lista,numeroserie,c,em);
                    try {
                        Desktop.getDesktop().open(new File(numeroserie + ".txt"));                        
                    } catch (IOException ex) {
                        Logger.getLogger(Imprimir.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    lista = new ArrayList<>();
                    request.getRequestDispatcher("Controlador?menu=NuevaVenta&accion=ventanueva").forward(request, response);
                    System.out.println("Venta Realizada con Éxito..!!!:" + r);
                    break;
                default:
                    v = new Venta();
                    lista = new ArrayList<>();
                    item = 0;
                    totalPagar = 0.0;
                    numeroserie = vdao.GenerarSerie();
                    if (numeroserie == null) {
                        numeroserie = "000000001";
                        request.setAttribute("nserie", numeroserie);
                    } else {
                        int incrementar = Integer.parseInt(numeroserie);
                        GenerarSerie gs = new GenerarSerie();
                        numeroserie = gs.NumeroSerie(incrementar);
                        request.setAttribute("nserie", numeroserie);
                    }
                    session.setAttribute("usuario", usuario);
                    request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
            }
        }
        if (menu.equals("EnviarCorreo")) {
            switch (accion) {
                case "New":
                    List<Cliente> clientes = cdao.listar();
                    request.setAttribute("clientes", clientes);
                    session.setAttribute("clientesSeleted", new ArrayList<Cliente>());
                    request.getRequestDispatcher("EnviarCorreo.jsp").forward(request, response);
                    break;
                case "add":
                    List<Cliente> getClientes = cdao.listar();
                    request.setAttribute("clientes", getClientes);
                    List<Cliente> selected = new ArrayList<>();
                    for (Cliente c : getClientes) {
                        String var = request.getParameter("txtSend" + c.getId());
                        if (var != null && var.equals("on")) {
                            selected.add(c);
                        }
                    }
                    session.setAttribute("clientesSeleted", selected);
                    request.setAttribute("clientes", getClientes);
                    request.getRequestDispatcher("EnviarCorreo.jsp").forward(request, response);
                    break;
                case "enviar":
                    List<Cliente> selectedToSend = (List<Cliente>) session.getAttribute("clientesSeleted");
                    String asunto = request.getParameter("txtAsunto");
                    String mensaje = request.getParameter("txtMensaje");
                    SendEmailUsingGMailSMTP.sendEmail(asunto, mensaje, selectedToSend);
                    request.getRequestDispatcher("Controlador?menu=EnviarCorreo&accion=New").forward(request, response);
                    break;
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
