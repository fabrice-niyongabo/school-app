# Watch the full video on YouTube
<a href="https://www.youtube.com/watch?v=RgZFoC0QHbg" target="_blank">
  <img src="https://img.youtube.com/vi/RgZFoC0QHbg/0.jpg" alt="Watch the video" style="width:100%">
</a>

## Getting started
To get started, create a .env file and provide the following environment variables
```
MYSQL_ROOT_PASSWORD=
MYSQL_DATABASE=
MYSQL_USER=
MYSQL_PASSWORD=
DB_URL=jdbc:mysql://yourMysqlServiceName:3306/yourDbName
```
Then run the app by using: ``docker compose up``

## Video resources
Below you can find all the basic commands and tools needed to deploy your full-stack application to a VPS

I assume you are using Ubuntu or Debian operating system on your VPS.

1. Installing docker on a VPS
```
sudo apt-get update
curl -sS https://get.docker.com/ | sh
```

For more information use the official docker website [https://docs.docker.com/engine/install/](https://docs.docker.com/engine/install/)

2. Installing nginx on a VPS

```
sudo apt update
sudo apt install nginx
sudo ufw allow 'Nginx Full'
```

### NGINX configuration template files
At this step, you must be able to access your application by using your VPS's IP address.
To be able to access your application by using a domain name, you need to make some NGINX configurations

1. NGINX configuration template file before installing an SSL certificate (when you want to access your application by using http://yourdomainname.com) 
```
server {
    listen 80;

    server_name yourdomainname.com;

    location / {
        proxy_pass http://127.0.0.1:yourApplicationPORT;
    }

    location ~ /.well-known/acme-challenge/ {
        root /var/www/certbot; # Ensure Certbot can validate SSL challenges
    }
}
```
NB: Replace both ``yourdomainname.com`` and ``yourApplicationPORT`` with your actual information



2. NGINX configuration template file after installing an SSL certificate

After installing an SSL certificate, you need to update the NGINX configuration file for your app with the following template:

```
server {
    listen 80;

    server_name yourdomainname.com;

    location ~ /.well-known/acme-challenge/ {
        root /var/www/certbot; # Ensure Certbot can validate SSL challenges
    }

    # force https
    return 301 https://$host$request_uri;
}

server {
    listen 443 ssl;
    server_name yourdomainname.com www.yourdomainname.com;

    ssl_certificate /etc/letsencrypt/live/yourdomainname.com/fullchain.pem;
    ssl_certificate_key /etc/letsencrypt/live/yourdomainname.com/privkey.pem;

    location / {
        proxy_pass http://127.0.0.1:yourApplicationPORT;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```

NB: Replace both ``yourdomainname.com`` and ``yourApplicationPORT`` with your actual information

# Congratulations 🍻

By following and implementing all the steps carefully, you should now be able to deploy your application on a VPS 🚀.
If you face any issues, just let me know in the comment section.

Don't forget to hit that like button as it helps this little channel grow🤗
Thank you!
