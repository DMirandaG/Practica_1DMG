package miranda.david.da.practica_1dmg.usuario;

public class Usuario {

    private String id;

    private String email;

    private String username;

    private String foto;

    public Usuario() {
        this("","","","");
    }

    public Usuario(String id) {
        this(id,"","","");
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Usuario(String id, String email, String username, String foto) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.foto = foto;
    }


}
