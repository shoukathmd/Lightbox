# Start from the Trino base image
FROM trinodb/trino

# Set the working directory in the container
WORKDIR /usr/src/app

# Copy your agent files into the container
COPY ./your-agent /usr/src/app/your-agent

# Install any necessary dependencies
# RUN apt-get update && apt-get install -y needed-packages

# You might need to set up your agent here (e.g., configuration files)

# Add a script to start both Trino and your agent
COPY ./start-services.sh /usr/src/app/start-services.sh
RUN chmod +x /usr/src/app/start-services.sh

# Set the script as the entry point
ENTRYPOINT ["/usr/src/app/start-services.sh"]
