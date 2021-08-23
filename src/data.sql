-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.26 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela desafio_mvc.local: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `local` DISABLE KEYS */;
INSERT INTO `local` (`id`, `cep`, `complemento`, `estado`, `logradouro`, `municipio`, `numero`, `nome`) VALUES
	(6, '8384000', 'PA', 'Paraná', 'Rua Pedro Zolner', 'Quitandinha', 125, 'Posto Central'),
	(7, '83840000', 'Hospital', 'Paraná', 'Rua Padre Francisco', 'Quitandinha', 47, 'Hospital Municipal');
/*!40000 ALTER TABLE `local` ENABLE KEYS */;

-- Copiando dados para a tabela desafio_mvc.lote: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` (`id`, `data_recebimento`, `data_validade`, `qnt_recebida`, `qnt_restante`, `vacina_id`, `identificacao`) VALUES
	(13, '2021-08-18 00:00:00.000000', '2021-12-20 00:00:00.000000', 1000, 996, 22, 'Corona Vac 001'),
	(14, '2021-08-01 00:00:00.000000', '2021-08-15 00:00:00.000000', 100, 100, 25, 'Jansenn 001 - Lote Vencido - Não Aparece Na Vacinacao'),
	(15, '2021-08-10 00:00:00.000000', '2022-01-21 00:00:00.000000', 1000, 999, 25, 'Jansenn 002');
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;

-- Copiando dados para a tabela desafio_mvc.pessoa: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `pessoa` DISABLE KEYS */;
INSERT INTO `pessoa` (`id`, `cpf`, `data_nascimento`, `cep`, `complemento`, `estado`, `logradouro`, `municipio`, `numero`, `nome`) VALUES
	(10, '35282758040', '2021-04-25 00:00:00.000000', '83840000', 'Casa', 'Paraná', 'Av Fernandes de andrade ', 'Quitandinha', 1405, 'Amanda Dias'),
	(11, '18046424013', '2003-06-05 00:00:00.000000', '83840000', 'Casa', 'Paraná', 'Rua do Expedicionario', 'Quitandinha', 1820, 'Hugo Diniz'),
	(12, '51126223000', '1998-12-20 00:00:00.000000', '83840000', 'Apartamento 12', 'Paraná', 'Rua do Limoeiro', 'Quitandinha', 2020, 'Pedro Amargo');
/*!40000 ALTER TABLE `pessoa` ENABLE KEYS */;

-- Copiando dados para a tabela desafio_mvc.vacina: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `vacina` DISABLE KEYS */;
INSERT INTO `vacina` (`id`, `is_dose`, `laboratorio`, `nome`, `intervalo`) VALUES
	(22, b'0', 'Butantan', 'CoronaVac', 30),
	(23, b'0', 'Universidade de Oxford', 'AstraZeneca', 90),
	(24, b'0', 'BioNtech', 'Pfizer', 45),
	(25, b'1', 'Janssen Farmacêutica', 'Jansenn', 0);
/*!40000 ALTER TABLE `vacina` ENABLE KEYS */;

-- Copiando dados para a tabela desafio_mvc.vacinacao: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `vacinacao` DISABLE KEYS */;
INSERT INTO `vacinacao` (`id`, `data_vacinacao`, `pessoa_id`, `dose`, `local_id`, `lote_id`) VALUES
	(58, '2021-08-18 00:00:00.000000', 10, 'Primeira', 7, 13),
	(59, '2021-08-18 00:00:00.000000', 11, 'Unica', 7, 15),
	(60, '2021-08-18 00:00:00.000000', 12, 'Segunda', 6, 13);
/*!40000 ALTER TABLE `vacinacao` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

SET FOREIGN_KEY_CHECKS=1;
