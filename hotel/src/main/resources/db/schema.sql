CREATE TABLE customers (
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    phone       VARCHAR(20)  NOT NULL,
    address     VARCHAR(255)
);

CREATE TABLE rooms (
    id               BIGSERIAL PRIMARY KEY,
    room_number      INTEGER      NOT NULL UNIQUE,
    type             VARCHAR(50)  NOT NULL,
    price_per_night  NUMERIC(10,2) NOT NULL,
    capacity         INTEGER      NOT NULL,
    description      VARCHAR(255),
    available        BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE bookings (
    id          BIGSERIAL PRIMARY KEY,
    customer_id BIGINT        NOT NULL REFERENCES customers(id),
    room_id     BIGINT        NOT NULL REFERENCES rooms(id),
    check_in    DATE          NOT NULL,
    check_out   DATE          NOT NULL,
    status      VARCHAR(50)   NOT NULL DEFAULT 'PENDING',
    total_cost  NUMERIC(10,2) NOT NULL,
    CONSTRAINT chk_dates CHECK (check_out > check_in)
);

CREATE INDEX idx_bookings_customer ON bookings(customer_id);
CREATE INDEX idx_bookings_room     ON bookings(room_id);
CREATE INDEX idx_bookings_dates    ON bookings(check_in, check_out);