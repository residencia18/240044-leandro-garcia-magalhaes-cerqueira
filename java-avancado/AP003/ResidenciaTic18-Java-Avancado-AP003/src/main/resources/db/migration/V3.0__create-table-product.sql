create table product (
  price numeric(38,2),
  stock integer not null,
  category_id bigint,
  id bigserial not null,
  chassis varchar(255),
  chipset varchar(255),
  cpu varchar(255),
  description varchar(255),
  memory varchar(255),
  name varchar(255),
  network varchar(255),
  operational_system varchar(255),
  slots varchar(255),
  storage varchar(255),
  url_image varchar(255),
  primary key (id)
);

-- Insert para servidor DELL
INSERT INTO product (price, stock, category_id, chassis, chipset, cpu, description, memory, name, network, operational_system, slots, storage, url_image
) VALUES (2500.00, 2, 1, 'DELL PowerEdge R740', 'Intel C621', 'Intel Xeon Silver 4214', 'High-performance server for demanding workloads', '64GB', 'DELL PowerEdge R740', 
  'Gigabit Ethernet', 'Windows Server 2019', '8x PCIe', '1TB SSD', 'http://example.com/dell_r740.jpg');

-- Insert para servidor POSITIVO
INSERT INTO product ( price, stock, category_id, chassis, chipset, cpu, description, memory, name, network, operational_system, slots, storage, url_image
) VALUES (1500.00, 3, 2, 'POSITIVO Master Server', 'AMD B550', 'AMD Ryzen 7 3700X', 'Reliable and cost-effective server solution', '32GB', 'POSITIVO Master Server', 
  'Gigabit Ethernet', 'Ubuntu Server 20.04', '4x PCIe', '512GB SSD', 'http://example.com/positivo_master.jpg');
