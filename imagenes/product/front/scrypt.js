async function getProductos() {
    let headersList = {
        "Accept": "*/*",
        "User-Agent": "Thunder Client (https://www.thunderclient.com)"
       }
       
       let response = await fetch("http://localhost:8081/listar", { 
         method: "GET",
         headers: headersList
       });
       
       let data = await response.text();
       console.log(data);
       
}

getProductos();