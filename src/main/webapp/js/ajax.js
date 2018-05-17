function ajax (method,url,data=null){
    return new Promise ( function(resolve,reject){
        let request ;
        if(window.XMLHttpRequest){
            request= new XMLHttpRequest();
        //resolve(datos)
        }else if(window.ActiveXObject){
            request = new ActiveXObject("Msxml2.XMLHTTP");
            
        }else{
            reject("tu navegador no soporta llamas ajax");
        }
        
            request.onreadystatechange = function(){
                if(request.readyState==4){
                    if(request.status >= 200 && request.status<=203){
                        resolve(JSON.parse(request.responseText));
                    }else{
                        reject(Error(request.statusText));
                    }
                
                }
            };
        //reject(error)
        request.open(method,url,true);
        request.setRequestHeader("Content-type","application/json");
        request.send(JSON.stringify(data));
        
    });
}
console.log("libreria cargada");