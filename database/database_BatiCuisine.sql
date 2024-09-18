-- Enumeration etat projet :
CREATE TYPE EtatProjet AS ENUM ('En_cours', 'Termine', 'Annule');

-- Table Clients :
CREATE TABLE clients (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(20) NOT NULL,
    adresse VARCHAR(50),
    telephone VARCHAR(15),
    estProfessionnel BOOLEAN
);

-- Table Projets :
CREATE TABLE projets (
    id SERIAL PRIMARY KEY,
    nomProjet VARCHAR(100),
    margeBeneficiaire DOUBLE PRECISION,
    coutTotal DOUBLE PRECISION,
    etatProjet EtatProjet,
    client_id INT REFERENCES clients(id) 
	);
-- Table Composants :
CREATE TABLE composants (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    typeComposant VARCHAR(50), 
    tauxTVA DOUBLE PRECISION,
    projet_id INT REFERENCES projets(id) ON DELETE CASCADE 
);
--table composants :
CREATE TABLE composants (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    typeComposant VARCHAR(50), 
    tauxTVA DOUBLE PRECISION,
    projet_id INT REFERENCES projets(id) ON DELETE CASCADE 
);

-- Table Matériaux qui hérite de Composants :
CREATE TABLE materiaux (
    coutUnitaire DOUBLE PRECISION,
    quantite DOUBLE PRECISION,
    coutTransport DOUBLE PRECISION,
    coefficientQualite DOUBLE PRECISION
) INHERITS (composants);

-- Table Main d'œuvre qui hérite de Composants:
CREATE TABLE main_oeuvre (
    tauxHoraire DOUBLE PRECISION,
    heuresTravail DOUBLE PRECISION,
    productiviteOuvrier DOUBLE PRECISION
) INHERITS (composants);

-- Table Devis 
CREATE TABLE devis (
    id SERIAL PRIMARY KEY,
    montantEstime DOUBLE PRECISION,
    dateEmission DATE,
    dateValidite DATE,
    accepte BOOLEAN,
    projet_id INT UNIQUE REFERENCES projets(id) 
);
