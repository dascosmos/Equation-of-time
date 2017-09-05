package Logica;

public class EoT {

	private double dia, mes, anio, hora, min, seg;
	private double diaC;
	private double div = 0.9863013699;
	private CargaParams params = new CargaParams();

	public void recibirDatos(double dia, double mes, double anio, double hora, double min, double seg) {

		this.dia = dia;
		this.mes = mes;
		this.anio = anio;
		this.hora = hora;
		this.min = min;
		this.seg = seg;
		diaCorrido(mes, dia, anio);
	}

	public String getFecha() {
		return (int) dia + " / " + (int) mes + " / " + (int) anio;
	}

	/*
	 * 1. CONVERSION DE CALENDARIO GREGORIANO A JULIANO TOMANDO EL UTC Y EL DT
	 */

	public double calculoDiaFrac() {

		double horaS = seg / 3600;
		double horaM = min / 60;
		double horas = hora + horaM + horaS;
		double horaD = horas / 24;
		double diaD = dia + horaD;

		return diaD;
	}

	public double calculohoras() {
		double horaS = seg / 3600;
		double horaM = min / 60;
		double horas = hora + horaM + horaS;
		return horas;

	}

	public double calculoJuliano() {
		int a, b;
		double jd;
		double dias = calculoDiaFrac();
		if (mes == 1 || mes == 2) {
			anio = anio - 1;
			mes = mes + 12;

		}

		a = (int) (anio / 100);
		b = (2 - a + ((int) (a / 4)));
		jd = (int) (365.25 * (anio + 4716)) + (int) (30.6001 * (mes + 1)) + dias + b - 1524.5;

		return jd;

	}

	public double deltaT() {
		double y = anio + ((mes - 0.5) / 12);
		double fi = y - 2000;
		double delta;

		if (anio <= 2050)
			delta = (62.92 + 0.32217 * fi + 0.005589 * Math.pow(fi, 2)) / 3600;
		else if (anio > 2050 && anio < 2150)
			delta = (-20 + 32 * Math.pow(((y - 1820) / 100), 2) - 0.5628 * (2150 - y)) / 3600;
		else
			delta = (-20 + 32 * Math.pow(((y - 1820) / 100), 2)) / 3600;
		return delta;
	}

	public double JDE() {
		double diac = calculoDiaFrac() + deltaT();
		int a, b;
		double jde;
		if (mes == 1 || mes == 2) {
			anio = anio - 1;
			mes = mes + 12;

		}

		a = (int) (anio / 100);
		b = (2 - a + ((int) (a / 4)));
		jde = (int) (365.25 * (anio + 4716)) + (int) (30.6001 * (mes + 1)) + diac + b - 1524.5;

		return jde;

	}

	public double centuriaJuliana() {
		double dj = calculoJuliano();
		double ti = (dj - 2451545) / 36525;
		return ti;
	}

	public double centuriaJDE(){
		double dj = JDE();
		double jce = (dj - 2451545) / 36525;
		return jce;
	}

	public double JME(){
		double jc = centuriaJDE();
		double jme = centuriaJDE()/10;
		return jme;
	}

	/* 2. CALCULO DEL LST Y LA ASCENSION RECTA DEL SOL */

	public double calculoNutacion() {
		double t = (JDE() - 2451545) / 36525;
		double d = (297.85036 + (445267.111480 * t) - (0.00197142 * (t * t)) + ((t * t * t) / 189474)) % 360;
		double m = (357.52772 + (35999.050340 * t) - (0.0001603 * (t * t)) - ((t * t * t) / 300000)) % 360;
		double ml = (134.96298 + (477198.867398 * t) + (0.0086972 * (t * t)) + ((t * t * t) / 56250)) % 360;
		double f = (93.27191 + (483202.017538 * t) - (0.0036825 * (t * t)) + ((t * t * t) / 327270)) % 360;
		double p = (125.04452 - (1934.136261 * t) + (0.0020708 * (t * t)) + ((t * t * t) / 450000));

		if (p < 0)
			p = 360 + p;
		else
			p = p % 360;
		// calculo de la maldita tabla larga

		double nut = ((-171996 - (174.2 * t)) * (Math.sin(p))
				+ (-13187 - (1.6 * t)) * (Math.sin(-2 * d + 2 * f + 2 * p))
				+ (-2274 - (0.2 * t)) * (Math.sin(2 * f + 2 * p)) + (2062 + (0.2 * t)) * (Math.sin(2 * p))
				+ (1426 - (3.4 * t)) * (Math.sin(m)) + (712 + (0.1 * t)) * (Math.sin(ml))
				+ (-517 + (1.2 * t)) * (Math.sin(-2 * d + m + 2 * f + 2 * p))
				+ (-386 - (0.4 * t)) * (Math.sin(2 * f + p)) + (-301 + (0 * t)) * (Math.sin(ml + 2 * f + 2 * p))
				+ (217 - (0.5 * t)) * (Math.sin(-2 * d - m + 2 * f + 2 * p))
				+ (-158 + (0 * t)) * (Math.sin(-2 * d + ml)) + (129 + (0.1 * t)) * (Math.sin(-2 * d + 2 * f + p))
				+ (123 + (0 * t)) * (Math.sin(-ml + 2 * f + 2 * p)) + (63 + (0 * t)) * (Math.sin(2 * d))
				+ (63 + (0.1 * t)) * (Math.sin(ml + p)) + (-59 + (0 * t)) * (Math.sin(2 * d - ml + 2 * f + 1 * p))
				+ (-58 + (0.1 * t)) * (Math.sin(-ml + p)) + (-51 + (0 * t)) * (Math.sin(ml + 2 * f + p))
				+ (48 + (0 * t)) * (Math.sin(-2 * d + 2 * ml)) + (46 + (0 * t)) * (Math.sin(-2 * ml + 2 * f + p))
				+ (-38 + (0 * t)) * (Math.sin(2 * d + 2 * f + 2 * p))
				+ (-31 + (0 * t)) * (Math.sin(2 * ml + 2 * f + 2 * p)) + (29 + (0 * t)) * (Math.sin(2 * ml))
				+ (29 + (0 * t)) * (Math.sin(-2 * d + ml + 2 * f + 2 * p)) + (26 + (0 * t)) * (Math.sin(2 * f))
				+ (-22 + (0 * t)) * (Math.sin(-2 * d + 2 * f)) + (21 + (0 * t)) * (Math.sin(-ml + 2 * f + p))
				+ (17 - (0.1 * t)) * (Math.sin(2 * m)) + (16 + (0 * t)) * (Math.sin(2 * d - ml + p))
				+ (-16 + (0.1 * t)) * (Math.sin(-2 * d + 2 * m + 2 * f + 2 * p)) + (-15 + (0 * t)) * (Math.sin(m + p))
				+ (-13 + (0 * t)) * (Math.sin(-2 * d + ml + p)) + (-12 + (0 * t)) * (Math.sin(-m + p))
				+ (11 + (0 * t)) * (Math.sin(2 * ml - 2 * f)) + (-10 + (0 * t)) * (Math.sin(2 * d - ml + 2 * f + p))
				+ (-8 + (0 * t)) * (Math.sin(2 * d + ml + 2 * f + 2 * p))
				+ (7 + (0 * t)) * (Math.sin(m + 2 * f + 2 * p)) + (-7 + (0 * t)) * (Math.sin(-2 * d + m + ml))
				+ (-7 + (0 * t)) * (Math.sin(-m + 2 * f + 2 * p)) + (-7 + (0 * t)) * (Math.sin(2 * d + 2 * f + p))
				+ (6 + (0 * t)) * (Math.sin(2 * d + ml)) + (6 + (0 * t)) * (Math.sin(-2 * d + 2 * ml + 2 * f + 2 * p))
				+ (6 + (0 * t)) * (Math.sin(-2 * d + ml + 2 * f + p)) + (-6 + (0 * t)) * (Math.sin(2 * d - 2 * ml + p))
				+ (-6 + (0 * t)) * (Math.sin(2 * d + p)) + (5 + (0 * t)) * (Math.sin(-m + ml))
				+ (-5 + (0 * t)) * (Math.sin(-2 * d - m + 2 * f + p)) + (-5 + (0 * t)) * (Math.sin(-2 * d + p))
				+ (-5 + (0 * t)) * (Math.sin(2 * ml + 2 * f + p)) + (4 + (0 * t)) * (Math.sin(-2d + 2 * ml + p))
				+ (4 + (0 * t)) * (Math.sin(-2 * d + m + 2 * f + p)) + (4 + (0 * t)) * (Math.sin(ml - 2 * f))
				+ (-4 + (0 * t)) * (Math.sin(-d + ml)) + (-4 + (0 * t)) * (Math.sin(-2 * d + m))
				+ (-4 + (0 * t)) * (Math.sin(d)) + (3 + (0 * t)) * (Math.sin(ml + 2 * f))
				+ (-3 + (0 * t)) * (Math.sin(-2 * ml + 2 * f + 2 * p)) + (-3 + (0 * t)) * (Math.sin(-d - m + ml))
				+ (-3 + (0 * t)) * (Math.sin(m + ml)) + (-3 + (0 * t)) * (Math.sin(-m + ml + 2 * f + 2 * p))
				+ (-3 + (0 * t)) * (Math.sin(2 * d - m - ml + 2 * f + 2 * p))
				+ (-3 + (0 * t)) * (Math.sin(3 * ml + 2 * f + 2 * p))
				+ (-3 + (0 * t)) * (Math.sin(2 * d - m + 2 * f + 2 * p))) * (0.0001 / 3600);
		return nut;

	}

	public double calculoOblicuidad() {
		double t = (JDE() - 2451545) / 36525;

		double d = (297.85036 + (445267.111480 * t) - (0.00197142 * (t * t)) + ((t * t * t) / 189474)) % 360;
		double m = (357.52772 + (35999.050340 * t) - (0.0001603 * (t * t)) - ((t * t * t) / 300000)) % 360;
		double ml = (134.96298 + (477198.867398 * t) + (0.0086972 * (t * t)) + ((t * t * t) / 56250)) % 360;
		double f = (93.27191 + (483202.017538 * t) - (0.0036825 * (t * t)) + ((t * t * t) / 327270)) % 360;
		double p = (125.04452 - (1934.136261 * t) + (0.0020708 * (t * t)) + ((t * t * t) / 450000));

		if (p < 0)
			p = 360 + p;
		else
			p = p % 360;

		// calculo de la otra maldita tabla larga

		double obl = ((92025 + (8.9 * t)) * (Math.cos(p))
				+ (5736 - (3.1 * t)) * (Math.cos((-2 * d) + (2 * f) + (2 * p)))
				+ (977 - (0.5 * t)) * (Math.cos((2 * f) + (2 * p))) + (-895 + (0.5 * t)) * (Math.cos(2 * p))
				+ (54 - (0.1 * t)) * (Math.cos(m)) + (-7 + (0 * t)) * (Math.cos(ml))
				+ (224 - (0.6 * t)) * (Math.cos((-2 * d) + m + (2 * f) + (2 * p)))
				+ (200 + (0 * t)) * (Math.cos((2 * f) + p)) + (129 - (0.1 * t)) * (Math.cos(ml + (2 * f) + (2 * p)))
				+ (-95 + (0.3 * t)) * (Math.cos((-2 * d) - m + (2 * f) + (2 * p)))
				+ (0 + (0 * t)) * (Math.cos((-2 * d) + ml)) + (-70 + (0 * t)) * (Math.cos((-2 * d) + (2 * f) + p))
				+ (-53 + (0 * t)) * (Math.cos(-ml + (2 * f) + (2 * p))) + (0 + (0 * t)) * (Math.cos(2 * d))
				+ (-33 + (0 * t)) * (Math.cos(ml + p)) + (26 + (0 * t)) * (Math.cos((2 * d) - ml + (2 * f) + (1 * p)))
				+ (32 + (0 * t)) * (Math.cos(-ml + p)) + (27 + (0 * t)) * (Math.cos(ml + (2 * f) + p))
				+ (0 + (0 * t)) * (Math.cos((-2 * d) + (2 * ml)))
				+ (-24 + (0 * t)) * (Math.cos((-2 * ml) + (2 * f) + p))
				+ (16 + (0 * t)) * (Math.cos((2 * d) + (2 * f) + (2 * p)))
				+ (13 + (0 * t)) * (Math.cos((2 * ml) + (2 * f) + (2 * p))) + (0 + (0 * t)) * (Math.cos(2 * ml))
				+ (-12 + (0 * t)) * (Math.cos((-2 * d) + ml + (2 * f) + (2 * p))) + (0 + (0 * t)) * (Math.cos(2 * f))
				+ (0 + (0 * t)) * (Math.cos((-2 * d) + (2 * f))) + (-10 + (0 * t)) * (Math.cos(-ml + (2 * f) + p))
				+ (0 + (0 * t)) * (Math.cos(2 * m)) + (-8 + (0 * t)) * (Math.cos((2 * d) - ml + p))
				+ (7 + (0 * t)) * (Math.cos((-2 * d) + (2 * m) + (2 * f) + (2 * p))) + (9 + (0 * t)) * (Math.cos(m + p))
				+ (7 + (0 * t)) * (Math.cos((-2 * d) + ml + p)) + (6 + (0 * t)) * (Math.cos(-m + p))
				+ (0 + (0 * t)) * (Math.cos((2 * ml) - (2 * f)))
				+ (5 + (0 * t)) * (Math.cos((2 * d) - ml + (2 * f) + p))
				+ (3 + (0 * t)) * (Math.cos((2 * d) + ml + (2 * f) + (2 * p)))
				+ (-3 + (0 * t)) * (Math.cos(m + (2 * f) + (2 * p))) + (0 + (0 * t)) * (Math.cos((-2 * d) + m + ml))
				+ (3 + (0 * t)) * (Math.cos(-m + (2 * f) + (2 * p))) + (3 + (0 * t)) * (Math.cos((2 * d) + (2 * f) + p))
				+ (0 + (0 * t)) * (Math.cos((2 * d) + ml))
				+ (-3 + (0 * t)) * (Math.cos((-2 * d) + (2 * ml) + (2 * f) + (2 * p)))
				+ (-3 + (0 * t)) * (Math.cos((-2 * d) + ml + (2 * f + p)))
				+ (3 + (0 * t)) * (Math.cos((2 * d) - (2 * ml) + p)) + (3 + (0 * t)) * (Math.cos((2 * d) + p))
				+ (0 + (0 * t)) * (Math.cos(-m + ml)) + (3 + (0 * t)) * (Math.cos((-2 * d) - m + (2 * f) + p))
				+ (3 + (0 * t)) * (Math.cos((-2 * d) + p)) + (3 + (0 * t)) * (Math.cos((2 * ml) + (2 * f) + p))
				+ (0 + (0 * t)) * (Math.cos((-2 * d) + (2 * ml) + p))
				+ (0 + (0 * t)) * (Math.cos((-2 * d) + m + (2 * f) + p)) + (0 + (0 * t)) * (Math.cos(ml - (2 * f)))
				+ (0 + (0 * t)) * (Math.cos(-d + ml)) + (0 + (0 * t)) * (Math.cos((-2 * d) + m))
				+ (0 + (0 * t)) * (Math.cos(d)) + (0 + (0 * t)) * (Math.cos(ml + (2 * f)))
				+ (0 + (0 * t)) * (Math.cos((-2 * ml) + (2 * f) + (2 * p))) + (0 + (0 * t)) * (Math.cos(-d - m + ml))
				+ (0 + (0 * t)) * (Math.cos(m + ml)) + (0 + (0 * t)) * (Math.cos(-m + ml + (2 * f) + (2 * p)))
				+ (0 + (0 * t)) * (Math.cos((2 * d) - m - ml + (2 * f) + (2 * p)))
				+ (0 + (0 * t)) * (Math.cos((3 * ml) + (2 * f) + (2 * p)))
				+ (0 + (0 * t)) * (Math.cos((2 * d) - m + (2 * f) + (2 * p)))) * (0.0001) / 3600;

		double u = t / 100;
		double eo = 23.4329111 - 1.300258333 * u - 0.0004305555556 * Math.pow(u, 2) + 0.555347222 * Math.pow(u, 3)
				- 0.014272222 * Math.pow(u, 4) - 0.069352777 * Math.pow(u, 5) - 0.010847222 * Math.pow(u, 6)
				+ 0.001977777777778 * Math.pow(u, 7) + 0.007741666667 * Math.pow(u, 8) + 0.001608333333 * Math.pow(u, 9)
				+ 0.0006805555556 * Math.pow(u, 10);
		double oblicuidad = eo + obl;
		System.out.println(oblicuidad);
		return oblicuidad;

	}
	//metodo para calcular el tiempo sideral medio (mean sidereal time)
	public double msd(){
		double dj = calculoJuliano();
		double t = (dj - 2451545) / 36525;
		double msd=((280.46061837 + 360.98564736629 * (dj - 2451545) + 0.000387933 * (t * t) - ((t * t * t) / 38710000)));
		return limit(msd);
	}
	//metodo para calcular el tiempo sideral aparente (apparent sidereal time)
	public double hGA() {
		double nut = calculoNutacion();
		double obl = calculoOblicuidad();
		double msd = msd();
		double ast = msd+nut*Math.cos(Math.toRadians(obl));
		return ast;

	}
	//metodo para calcular el tiempo sideral aparente (LMST)
	public double LST(double longitud){
		double lst = ((hGA()*24)/360) + ((longitud*24)/360);
		if(lst < 0){
		    return 24 + lst;
        }else if(lst > 24){
		    return 0 + lst;
        }else {
            return lst;
        }
	}

	/* 5. CALCULO DE COORDENADAS TOPOCENTRICAS*/

	//Método que calcula el angulo horario del sol en la posicion del observador.
	public double localHA(double longitude){
		double asd = hGA();
		double ra = GSunRA();
		double h = asd+longitude-ra;
		return limit(h);
	}

	//Metodo que calcula el paralaje horizontal ecuatorial del sol
	public double sunParallax(){
		double radius = HRadiusVect();
		double epsilon = 8.794/(3600*radius);
		return epsilon;
	}

	//Metodo que calcula el parametro u
	public double u(double latitude){
		double u = Math.atan(0.99664719*Math.tan(Math.toRadians(latitude)));
		return u;
	}

	//Metodo para calcular el termino x
	public double x(double elevation, double latitude){
		double u = u(latitude);
		double x = Math.cos(u)+(elevation/6378140)*Math.cos(Math.toRadians(latitude));
		return x;
	}

	//Metodo para calcular el termino y
	public double y(double elevation, double latitude){
		double u = u(latitude);
		double y = 0.99664719*Math.sin(u)+(elevation/6378140)*Math.sin(Math.toRadians(latitude));
		return y;
	}

	//Metodo para calcular el paralaje en la ascencion recta del sol
	public double RAparalax(double elevation, double latitude,double longitude){
		double x = x(elevation, latitude);
		double h = Math.toRadians(localHA(longitude));
		double epsilon = Math.toRadians(sunParallax());
		double dec = Math.toRadians(GSundec());
		double deltaAlpha = Math.atan2((-x*Math.sin(epsilon)*Math.sin(h)),(Math.cos(dec)-x*Math.sin(epsilon)*Math.cos(h)));
		return Math.toDegrees(deltaAlpha);
	}

	//metodo para calcular la ascencion recta topocentrica del sol
	public double topoRA(double elevation, double latitude,double longitude){
		double deltaalpha=RAparalax(elevation,latitude,longitude);
		double alpha = GSunRA();
		double alphaprima = alpha+deltaalpha;
		return alphaprima;
	}

	public double topoDec(double elevation, double latitude,double longitude){
		double dec = Math.toRadians(GSundec());
		double x = x(elevation, latitude);
		double y = y(elevation, latitude);
		double epsilon = Math.toRadians(sunParallax());
		double h = Math.toRadians(localHA(longitude));
		double deltaA = Math.toRadians(RAparalax(elevation, latitude, longitude));
		double deltaPrima = Math.atan2(((Math.sin(dec)-y*Math.sin(epsilon))*Math.cos(deltaA)),(Math.cos(dec)-x*Math.sin(epsilon)*Math.cos(h)));
		return Math.toDegrees(deltaPrima);
	}

	//Metodo para calcular el angulo horario topocentrico

	public double topoLocalHourAngle(double elevation, double latitude, double longitude){
		double deltaalpha=RAparalax(elevation,latitude,longitude);
		double h = localHA(longitude);
		double topoH = h-deltaalpha;
		return topoH;
	}

	//Metodo para calcular el angulo de elevacion topocentrico
	public double topoElevationAngle(double elevation, double latitude, double longitude){
		double topoLHA=Math.toRadians(topoLocalHourAngle(elevation, latitude, longitude));
		double lat = Math.toRadians(latitude);
		double dec = Math.toRadians(topoDec(elevation, latitude, longitude));
		double ezero = Math.asin(Math.sin(lat)*Math.sin(dec)+Math.cos(lat)*Math.cos(dec)*Math.cos(topoLHA));
		return Math.toDegrees(ezero);
	}

	//Metodo para calcular la correccion de refraccion atmosferica (futuro)

	//Metodo para calcular el angulo azimutal topocentrico

	public double topoAzimuthAngle(double elevation, double latitude, double longitude){
		double topoLHA=Math.toRadians(topoLocalHourAngle(elevation, latitude, longitude));
		double lat = Math.toRadians(latitude);
		double dec = Math.toRadians(topoDec(elevation, latitude, longitude));
		double azimuth = Math.atan2(Math.sin(topoLHA),(Math.cos(topoLHA)*Math.sin(lat)-Math.tan(dec)*Math.cos(lat)));
		return limit(Math.toDegrees(azimuth)+180);
	}




	/* 4. CALCULO DE LA ECUACION DEL TIEMPO */

	public double eqt() {
		double juliano = calculoJuliano();
		System.out.println("Dia juliano " + juliano);
		// Siglos Julianos desde 01/01/1900
		double t = centuriaJuliana();
		System.out.println("centuria juliana " + t);
		// Anomalia media del Sol

		double ms = (((-.0000033 * t - .00015) * t + 35999.04975) * t + 358.47583);
		ms = (ms - Math.floor(ms / 360) * 360) * Math.PI / 180;

		// Longitud ecliptica media del Sol
		double ls = Math.toRadians(HLongitude());

		// Oblicuidad de la ecliptica
		double E = Math.toRadians(calculoOblicuidad());
		// Excentricidad
		double exc = (-1.26e-07 * t - .0000418) * t + 1.675104e-02;

		// Ecuacion del tiempo
		double yt = Math.tan(E / 2) * Math.tan(E / 2);
		System.out.println(yt);

		double eq = (((-(2 * exc * Math.sin(ms)) - ((5 / 4) * exc * exc * Math.sin(2 * ms))) + yt * Math.sin(2 * ls)) - (.5 * yt * yt * Math.sin(4 * ls))) + (4 * exc * yt * Math.sin(ms) * Math.cos(2 * ls));

		eq = ((eq * 180 / Math.PI) / 15);
		System.out.println("eq " + eq);
		double eqtiempo = (eq * 60);
		System.out.println("eqtiempo " + eqtiempo);
		return eqtiempo;
	}


	// 5. Conversion a formato de HH,MM,SS

	public String Conversioneq(double entrada) {
		String signo;
		if (entrada > 0) {
			signo = "+";
		} else {
			signo = "-";
		}

		int minutos = (int) Math.floor(Math.abs(entrada));

		double segundos = Math.round(60 * (Math.abs(entrada) - minutos));

		String tiempo = signo + " " + minutos + "\' " + segundos + "\'\'";

		return tiempo;

	}

	public String conversionHora(double entrada) {
		double lst = entrada;
		double mi, se;
		int hora, min, seg;
		hora = (int) lst;
		mi = (lst - hora) * 60;
		min = (int) mi;
		se = (mi - min) * 60;
		seg = (int) se;
		return hora + ":" + min + ":" + seg;

	}

	public String conversionGrado(double entrada) {
		double lst = entrada;
		double mi, se;
		int hora, min, seg;
		hora = (int) lst;
		mi = Math.abs((lst - hora) * 60);
		min = (int) mi;
		se = Math.abs((mi - min) * 60);
		seg = (int) se;
		return hora + " deg " + min + "' " + seg + "''";

	}

	public void diaCorrido(double mesi, double diai, double anio) {

		int[] diasDeMeses = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31, 30, 31 };

		int sumaDeDias = -1;
		for (int i = 0; i < mesi - 1; i++) {

			sumaDeDias += diasDeMeses[i];
		}
		sumaDeDias += diai;
		diaC = sumaDeDias;

	}
	//Heliocentric longitude
	public double HLongitude(){
    	double jme = JME();
		double l0=params.L0(jme);
		double l1=params.L1(jme);
		double l2=params.L2(jme);
		double l3=params.L3(jme);
		double l4=params.L4(jme);
		double l5=params.L5(jme);

		double longitude = Math.toDegrees((l0+l1*jme+l2*Math.pow(jme,2)+l3*Math.pow(jme,3)+l4*Math.pow(jme,4)+l5*Math.pow(jme,5))/Math.pow(10,8));
		return limit(longitude);
	}

	public double HLatitude(){
		double jme = JME();
		double b0 = params.B0(jme);
		double b1 = params.B1(jme);
		double latitude = Math.toDegrees((b0+b1*jme)/Math.pow(10,8));
		System.out.println("Latitud heliocentrica: "+latitude);
		return latitude;
	}

	public double HRadiusVect(){
		double jme = JME();
		double r0 = params.R0(jme);
		double r1 = params.R1(jme);
		double r2 = params.R2(jme);
		double r3 = params.R3(jme);
		double r4 = params.R4(jme);

		double radius = (r0+r1*jme+r2*Math.pow(jme,2)+r3*Math.pow(jme,3)+r4*Math.pow(jme,4))/Math.pow(10,8);
		System.out.println("Vector radial: "+radius);
		return radius;
	}

	public double limit(double degree) {
		double to360 = degree / 360;
		double limdegree = 360 * (to360 - Math.floor(to360));
		if (limdegree < 0) {
			limdegree += 360;
		}
		return limdegree;
	}

	public double GLongitude(){
		double glongitude = HLongitude()+ 180;
		return limit(glongitude);
	}

	public double GLatitude(){
		double glatitude = -HLatitude();
		System.out.println("latitud geocentrica: "+glatitude);
		return glatitude;
	}

	public double AberrationCorrec(){
		double abr = -(20.4898/(3600*HRadiusVect()));
		System.out.println("Correccion de aberracion: "+abr);
		return abr;
	}

	public double ApparentSunLong(){
		double longitude = GLongitude()+ calculoNutacion()+ AberrationCorrec();
		System.out.println("Longitud aparante del sol: "+longitude);
		return longitude;
	}

	public double GSunRA(){
		double appSun = Math.toRadians(ApparentSunLong());
		double obl = Math.toRadians(calculoOblicuidad());
		double glat = Math.toRadians(GLatitude());
		double alpha = Math.atan2((Math.sin(appSun)*Math.cos(obl))-(Math.tan(glat)*Math.sin(obl)),Math.cos(appSun));
		System.out.println("ascencion recta del sol: "+alpha);
		return limit(Math.toDegrees(alpha));
	}

	public double GSundec(){
		double declination = Math.sin(Math.sin(Math.toRadians(GLatitude()))*Math.cos(Math.toRadians(calculoOblicuidad()))+Math.cos(Math.toRadians(GLatitude()))*Math.sin(Math.toRadians(calculoOblicuidad()))*Math.sin(Math.toRadians(ApparentSunLong())));
		System.out.println("declinacion del sol: "+declination);
		return Math.toDegrees(declination);
	}

	public double DectoHour(double ra){
		double righta = (ra*24)/360;
		return righta;
	}

}
