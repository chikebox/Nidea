package com.ipartek.formacion.nidea.ejemplos.graficos;

public abstract class ObjetoGrafico implements Imprimible{
	int x, y;
	public ObjetoGrafico() {
		super();
		x=0;
		y=0;
	}
	public void mover(int nuevaX, int nuevaY) {
		x=nuevaX;
		x=nuevaY;
	}
	public abstract void dibujar();

}
