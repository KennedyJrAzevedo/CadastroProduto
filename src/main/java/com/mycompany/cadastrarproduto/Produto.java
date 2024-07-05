package com.mycompany.cadastrarproduto;


public class Produto extends ProdutoDAO{

    private int id_produto;

    
    private String nome;
    private int categoria;
    private String descricao;
    private String codBarras;
    private int marca;
    private double peso;
    private double preco;

    public Produto() {
        this.nome = "";
        this.categoria = 0;
        this.descricao = "";
        this.codBarras = "";
        this.marca = 0;
        this.peso = 0.0;
        this.preco = 0.0;
    }

    public int getId_produto() {
        return id_produto;
    }

    public void setId_produto(int id_produto) {
        this.id_produto = id_produto;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String escricao) {
        this.descricao = escricao;
    }

    public String getCodBarras() {
        return codBarras;
    }

    public void setCodBarras(String codBarras) {
        this.codBarras = codBarras;
    }

    public int getMarca() {
        return marca;
    }

    public void setMarca(int marca) {
        this.marca = marca;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void inserirProduto() {
        insertMySQL(getNome(), getCategoria(),getDescricao(), getCodBarras(), getMarca(), getPreco(), getPeso());
    }
    public void alterarProduto(){
        super.atualizarProduto(id_produto, nome, categoria, descricao, codBarras, marca, preco, peso);
    }
    public void excluirProduto(){
        super.excluirProduto(id_produto);
    }
}
