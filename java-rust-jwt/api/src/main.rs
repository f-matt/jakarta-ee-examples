use std::net::SocketAddr;

use axum::{routing::get, Router};
use tokio::net::TcpListener;

#[tokio::main]
async fn main() {
    println!("Bootstraping service...");

    let address = SocketAddr::from(([127, 0, 0, 1], 5000));
    let listener = TcpListener::bind(address).await.unwrap();

    let app = Router::new().route("/api/", get(index)).into_make_service();

    println!("Server listening on localhost:5000");
    axum::serve(listener, app).await.unwrap();
}

async fn index() -> Result<String, String> {
    Ok("Status: online".to_string())
}
