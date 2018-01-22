FROM docker.elastic.co/kibana/kibana-oss:6.1.1
COPY ./wait-for-it.sh /usr/local/bin/
USER root
RUN yum -y install which
USER kibana
CMD ["/bin/bash", "/usr/local/bin/wait-for-it.sh", "elasticsearch:9200", "--", "/usr/local/bin/kibana-docker"]