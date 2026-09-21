-- PostgreSQL setup script for LabU Reserve
-- Run as the postgres superuser:
--   psql -U postgres -w -d postgres -f database-setup.sql
-- Then use labureserve/labureserve credentials for the application.

-- 1. Create the application database
CREATE DATABASE labureserve
    WITH OWNER = postgres
         ENCODING = 'UTF8'
         LC_COLLATE = 'en_US.UTF-8'
         LC_CTYPE = 'en_US.UTF-8'
         TEMPLATE = template0;

-- 2. Create the application user
CREATE USER labureserve WITH PASSWORD 'labureserve';

-- 3. Grant all privileges on the database
GRANT ALL PRIVILEGES ON DATABASE labureserve TO labureserve;

-- 4. Make labureserve the database owner (PostgreSQL 15+ needs this
--    for the user to have CREATE on the public schema)
ALTER DATABASE labureserve OWNER TO labureserve;

-- 5. Grant schema privileges (PostgreSQL 15+ restriction: users are no longer
--    automatically granted CREATE on the public schema)
\c labureserve
GRANT ALL ON SCHEMA public TO labureserve;
ALTER SCHEMA public OWNER TO labureserve;

-- If you get "peer authentication failed" or "password authentication failed":
--   Temporarily set trust auth in pg_hba.conf, then revert to scram-sha-256
--   See README.md for details.
