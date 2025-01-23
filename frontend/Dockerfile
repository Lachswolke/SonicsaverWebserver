# syntax=docker/dockerfile:1.4

# Create image based on the official Node image from dockerhub
FROM node:lts-buster AS development

# Set the working directory
WORKDIR /usr/src/app

# Copy dependency definitions
# Ensure package.json and package-lock.json exist before copying
COPY ./package.json ./package-lock.json /usr/src/app/

# Install dependencies
RUN npm ci

# Ensure proper permissions for node_modules (optional for local development)
RUN chown -R node:node /usr/src/app/node_modules

# Copy the rest of the application code
COPY . /usr/src/app

# Expose the port the app runs on
EXPOSE 3000

# Serve the app
CMD ["npm", "start"]

# Add development environment layer
FROM development AS dev-envs

# Install additional tools
RUN apt-get update && apt-get install -y --no-install-recommends git \
    && useradd -s /bin/bash -m vscode \
    && groupadd docker \
    && usermod -aG docker vscode \
    && rm -rf /var/lib/apt/lists/*

# Install Docker tools (cli, buildx, compose)
COPY --from=gloursdocker/docker / /

# Ensure react-scripts has execute permissions
RUN chmod +x /usr/src/app/node_modules/.bin/react-scripts

# Serve the app with npm
CMD ["npm", "start"]
