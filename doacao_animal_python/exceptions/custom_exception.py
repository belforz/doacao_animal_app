class CustomException(Exception):
    def __init__(self, safe_message: str, cause: Exception = None):
        super().__init__(safe_message)
        if cause:
            print(f"Erro interno: {repr(cause)}")
        
    def __str__(self):
        return f"CustomException: {self.args[0]}"