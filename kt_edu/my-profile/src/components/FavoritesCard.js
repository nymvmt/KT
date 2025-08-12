export default function FavoritesCard({ 
  title, 
  items, 
  icon, 
  className = '',
  variant = 'grid' // 'grid' or 'color'
}) {
  if (variant === 'color') {
    return (
      <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
        <h2 className="text-2xl font-bold mb-6 text-white flex items-center">
          <span className="text-3xl mr-3">{icon}</span>
          {title}
        </h2>
        <div className="grid grid-cols-2 gap-4">
          {items.map((item, index) => (
            <div 
              key={index}
              className={`relative overflow-hidden bg-gradient-to-br ${item.color} rounded-xl p-6 text-center hover:scale-105 transition-all duration-300 shadow-lg`}
            >
              <div className="text-2xl mb-2">{item.icon}</div>
              <p className="text-white font-bold">{item.name}</p>
              <p className={`text-${item.color.split('-')[1]}-100 text-sm`}>{item.description}</p>
            </div>
          ))}
        </div>
      </div>
    );
  }

  return (
    <div className={`bg-gray-800/50 backdrop-blur-md rounded-2xl p-8 border border-gray-700/50 ${className}`}>
      <h2 className="text-2xl font-bold mb-6 text-white flex items-center">
        <span className="text-3xl mr-3">{icon}</span>
        {title}
      </h2>
      <div className="grid grid-cols-2 gap-4">
        {items.map((item, index) => (
          <div 
            key={index}
            className={`group relative overflow-hidden bg-gradient-to-br ${item.color}/20 rounded-xl p-4 border border-${item.color.split('-')[1]}-500/30 hover:scale-105 transition-all duration-300`}
          >
            <div className="text-3xl mb-2">{item.icon}</div>
            <p className="text-white font-medium">{item.name}</p>
            <p className="text-gray-400 text-sm">{item.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
} 