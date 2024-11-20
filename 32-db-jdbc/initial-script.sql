create table venda (
id bigint not null auto_increment,
nome_cliente varchar(60) not null,
valor_total decimal(10,2) not null,
data_pagamento date not null,

primary key (id)
);

INSERT INTO venda (nome_cliente, valor_total, data_pagamento)
VALUES ('João D''arc', 1400.30, '2023-04-06');

INSERT INTO venda (nome_cliente, valor_total, data_pagamento)
VALUES ('Sebastião Moura', 8920, '2023-04-01');

INSERT INTO venda (nome_cliente, valor_total, data_pagamento)
VALUES ('Maria Abadia', 15640, '2023-04-10');
