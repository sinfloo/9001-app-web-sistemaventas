<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <form action="Controlador?menu=Producto" method="POST">
                        <div class="form-group">
                            <label>Producto</label>
                            <input type="text" value="${producto.getNom()}" name="txtDni" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Precio</label>
                            <input type="text" value="${producto.getPre()}" name="txtNombres" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Stock</label>
                            <input type="text" value="${producto.getStock()}" name="txtTel" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <select class="form-control" name="txtEstado">
                                <option value="1">ACTIVO</option>
                                <option value="0">INACTIVO</option>
                            </select>
                        </div>                        
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>                         
            </div>
        </div>                     
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <table class="table table-hover" style="width: 100%" id="example">
                        <thead>
                            <tr class="text-center">
                                <th>#</th>
                                <th>Nombres</th>
                                <th>Precio</th>
                                <th>Stock</th>                                    
                                <th>ESTADO</th>                                    
                                <th>ACCION</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <c:forEach var="em" items="${productos}">
                                <tr>
                                    <td class="text-center">${em.getId()}</td>                                      
                                    <td>${em.getNom()}</td>
                                    <td>${em.getPre()}</td>
                                    <td>${em.getStock()}</td>
                                    <c:if test="${em.getEstado()=='1'}">
                                        <td class="text-center">ACTIVO</td>
                                    </c:if>
                                    <c:if test="${em.getEstado()!='1'}">
                                        <td class="text-center">INACTIVO</td>
                                    </c:if>
                                    <td class="text-center">
                                        <a class="btn btn-outline-warning btn-sm" href="Controlador?menu=Producto&accion=Editar&id=${em.getId()}"><i class="bi bi-pencil-square"></i></a>
                                        <a class="btn btn-outline-danger btn-sm" href="Controlador?menu=Producto&accion=Delete&id=${em.getId()}"><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>      
</div>      
<%@ include file="commons/footer.jspf"%>


