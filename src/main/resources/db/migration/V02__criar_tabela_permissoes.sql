CREATE TABLE IF NOT EXISTS `permissoes` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `role` varchar(50) NOT NULL,
    `descricao` varchar(200) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;