/*StringRequest stringRequest = new StringRequest(Request.Method.POST, conexion.getRuta(), new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(Inicio.this, "-"+response, Toast.LENGTH_SHORT).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Inicio.this, "->"+error, Toast.LENGTH_SHORT).show();
                            }
                        }
                        ){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> parametros = new HashMap<String,String>();
                                String Nombre = Correo;
                                String Dato = Pass;
                                parametros.put("NOMBRE",Nombre);
                                parametros.put("DATO",Dato);
                                return parametros;
                            }
                        };*/