$("tr #Cant").click(function (e) {
    var idp = $(this).parent().find('#item1').val();
    var cantidad = $(this).parent().find('#Cant').val();
    var url = "Controlador?menu=NuevaVenta&accion=updateCant";
    console.log(idp, cantidad, url);
    $.ajax({
        type: 'POST',
        url: url,
        data: "id=" + idp + "&cantidad=" + cantidad,
        success: function (data, textStatus, jqXHR) {
            parent.location.href = "Controlador?menu=NuevaVenta&accion=updateCant";
        }
    });
});


$("tr #deleteItem").click(function (e) {
    e.preventDefault();
    var idp = $(this).parent().find('#item2').val();
    swal({
        title: "Esta Seguro de Eliminar?",
        text: "Una una Vez Eliminado, Debera Agregar de Nuevo!",
        icon: "warning",
        buttons: true,
        dangerMode: true
    }).then((willDelete) => {
        if (willDelete) {
            eliminar(idp);
            swal(" ¡Oh! ¡Registro Borrado! ", {
                icon: "success",
            }).then((willDelete) => {
                if (willDelete) {
                    parent.location.href = "Controlador?menu=NuevaVenta&accion=updateCant";
                }
            });
        }
    });
});
function eliminar(idp) {
    var url = "Controlador?menu=NuevaVenta&accion=delete&id=" + idp;
    console.log("hol");
    $.ajax({
        type: 'POST',
        url: url,
        async: true,
        success: function (r) {
        }
    });
}