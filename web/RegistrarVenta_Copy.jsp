<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>
<div class="">
    <div class="modal">
        <form action="Controlador?menu=NuevaVenta" method="POST">
            <div class="card-body">
                <!--DATOS DEL PRODUCTO-->
                <label>Datos Producto</label>
                <div class="row">
                    <div class="form-group col-lg-6">
                        <div class="input-group">
                            <div class="custom-file">
                                <input type="text" name="codigoproducto" value="${producto.getId()}" class="form-control" placeholder="Codigo">
                            </div>
                            <div class="input-group-append">
                                <button type="submit" name="accion" value="BuscarProducto" class="btn btn-outline-info">Buscar</button>
                            </div>
                        </div>
                    </div>   
                    <div class="form-group col-lg-6">
                        <div class="input-group">  
                            <input type="text" name="nomproducto" value="${producto.getNom()}" placeholder="Datos Producto" class="form-control form-control-sm">
                        </div>
                    </div>
                    <div class="form-group col-lg-6">
                        <div class="input-group">  
                            <input type="text" name="precio" value="${producto.getPre()}" class="form-control" placeholder="S/.0.00" required="">
                        </div>
                    </div>
                    <div class="form-group col-lg-3">
                        <div class="input-group">  
                            <input type="number" value="1" name="cant" placeholder="" class="form-control" required="">
                        </div>
                    </div>
                    <div class="form-group col-lg-3">
                        <div class="input-group">  
                            <input type="text" name="stock" value="${producto.getStock()}" placeholder="Stock" class="form-control">
                        </div>
                    </div>
                </div>
                <!--BOTON AGREGAR PRODUCTO AL REGISTRO-->
                <div class="form-group">
                    <div class="col-sm">
                        <button type="submit" name="accion" value="Agregar" class="btn btn-outline-primary">Agregar Producto</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="">
        <div class="card">       
            <div class="card-header">
                <!--DATOS DE LA EMPRESA-->
                <div class="row">
                    <div class="col-lg-5">
                        
                    </div>
                    <div class="col-lg-7 text-center">
                        <h3>CENTRO DE VENTAS</h3>
                        <p><strong>DIRECCION:</strong> LOS VENCEDORES 123 <strong>TELEFONO:</strong> +51910225993</p>
                    </div>
                </div>  
            </div>
            <div class="card-body">                                        
                <!--DATOS DEL CLIENTE-->
                <label>Datos del Cliente</label>
                <div class="row">                            
                    <div class="form-group col-lg-4">
                        <form action="Controlador?menu=NuevaVenta" method="POST">
                            <div class="input-group">
                                <input type="text" name="codigocliente" value="${c.getDni()}" class="form-control form-control-sm" placeholder="Codigo" required="">
                                <button type="submit" name="accion" value="BuscarCliente" class="btn btn-sm btn-outline-info btn-sm">Buscar</button>
                            </div>
                        </form>                               
                    </div>   
                    <div class="form-group col-lg-4">
                        <div class="input-group ">  
                            <input type="text" name="nombrescliente" value="${c.getNom()}" placeholder="Datos Cliente" class="form-control form-control-sm">
                        </div>
                    </div>
                    <div class="form-group col-lg-4">
                        <div class="input-group ">  
                            <input readonly="" type="text" name="numeroserie" class="form-control form-control-sm text-center" value="SERIE NRO: ${nserie}" style="font-weight: bold">
                        </div>
                    </div>
                </div>
                <div class="table-responsive-sm">
                    <table class="table table-sm ">
                        <thead class="table-dark">
                            <tr class="text-center">
                                <th>N°</th>
                                <th>ID</th>
                                <th>PRODUCTO</th>
                                <th>PRECIO</th>
                                <th>CANTIDAD</th>
                                <th>SUBTOTAL</th>                                    
                                <th class="accion">ACCION</th>                                    
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="list" items="${lista}">
                                <tr class="text-center">
                                    <td>${list.getItem()}</td>
                                    <td>${list.getIdproducto()}</td>
                                    <td>${list.getDescripcionP()}</td>
                                    <td>${list.getPrecio()}</td>
                                    <td>${list.getCantidad()}</td>
                                    <td>${list.getSubtotal()}</td>
                                    <td class="d-flex">
                                        <a href="#" class="btn btn-warning">Editar</a>
                                        <a href="#" class="btn btn-danger" style="margin-left: 5px">Delete</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>                       
            </div>
            <div class="card-footer" >
                <div class="row">
                    <div class="col-sm-6">
                        <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" class="btn btn-success">Generar Venta</a>
                        <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
                    </div>
                    <div class="col-sm-6 ml-auto d-flex">                                
                        <label class=" col-sm-6 text-right mt-2">Total a Pagar</label>                                                       
                        <input type="text" name="txtTotal" value="S/.${totalpagar}0" class="form-control text-center font-weight-bold" style="font-size: 18px;">
                    </div>
                </div>                        
            </div>
        </div>

    </div>
</div>
<%@ include file="commons/footer.jspf"%>
